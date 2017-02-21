package com.netty.httpfile;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * description: 文件上传
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-05 11:29
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    // 文件是否被允许访问下载验证
    private static final Pattern ALLOWED_FILE_NAME      = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");
    // 非法的 URI 正则
    private static final Pattern INSECURE_URI           = Pattern.compile(".*[<>&\"].*");
    public static final String   HTTP_DATE_FORMAT       = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String   HTTP_DATE_GMT_TIMEZONE = "GMT";
    public static final int      HTTP_CACHE_SECONDS     = 60;

    private String               url;

    public HttpFileServerHandler(String url){
        this.url = url;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        // 对请求的解码结果进行判断
        if (!request.decoderResult().isSuccess()) {
            // 400
            sendError(ctx, BAD_REQUEST);
            return;
        }

        // 对请求方式进行判断：如果不是GET方式（或POST方式）则返回异常
        if (request.method() != HttpMethod.GET) {
            // 405
            sendError(ctx, METHOD_NOT_ALLOWED);
            return;
        }

        // 获取请求的 uri 路径
        String uri = request.uri();
        // 对 url 进行分析，返回本地系统
        String path = sanitizeUri(uri);
        // 如果路径构造不合法，则 path 为 null
        if (path == null) {
            // 403
            sendError(ctx, FORBIDDEN);
            return;
        }

        // 创建 file 对象
        File file = new File(path);
        // 判断文件是否为隐藏或不存在
        if (file.isHidden() || !file.exists()) {
            // 404
            sendError(ctx, NOT_FOUND);
            return;
        }

        // 如果为文件夹
        if (file.isDirectory()) {
            if (uri.endsWith("/")) {
                // 如果以正常“/”，说明访问一个文件目录，则进行展示文件列表
                sendListing(ctx, file);
            } else {
                // 如果非“/”结尾，则重定向，补全"/"，再次请求
                sendRedirect(ctx, uri + '/');
            }
            return;
        }
        // 如果所创建的file对象不是文件类型
        if (!file.isFile()) {
            // 403
            sendError(ctx, FORBIDDEN);
            return;
        }

        // Cache Validation
        String ifModifiedSince = request.headers().get(IF_MODIFIED_SINCE);
        if (ifModifiedSince != null && !ifModifiedSince.isEmpty()) {
            DateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
            Date ifModifiedSinceDate = dateFormatter.parse(ifModifiedSince);
            // Only compare up to the second because the datetime format we send
            // to the client
            // does not have milliseconds
            long ifModifiedSinceDateSeconds = ifModifiedSinceDate.getTime() / 1000;
            long fileLastModifiedSeconds = file.lastModified() / 1000;
            if (ifModifiedSinceDateSeconds == fileLastModifiedSeconds) {
                sendNotModified(ctx);
                return;
            }
        }
        // 随机文件读写类
        RandomAccessFile raf;
        try {
            // 以只读的方式打开文件
            raf = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException ignore) {
            // 404
            sendError(ctx, NOT_FOUND);
            return;
        }
        long fileLength = raf.length();
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        // 设置响应信息
        HttpUtil.setContentLength(response, fileLength);
        // 设置响应头
        setContentTypeHeader(response, file);
        setDateAndCacheHeaders(response, file);
        if (HttpUtil.isKeepAlive(request)) {
            response.headers().set("CONNECTION", HttpHeaderValues.KEEP_ALIVE);
        }

        // 进行写出
        ctx.write(response);

        // 构造发送文件线程，将文件写入到 Chunked 缓冲区
        ChannelFuture sendFileFuture;
        if (ctx.pipeline().get(SslHandler.class) == null) {
            sendFileFuture = ctx.write(new DefaultFileRegion(raf.getChannel(), 0, fileLength),
                                       ctx.newProgressivePromise());
        } else {
            sendFileFuture = ctx.write(new HttpChunkedInput(new ChunkedFile(raf, 0, fileLength, 8192)),
                                       ctx.newProgressivePromise());
        }

        // 添加传输监听
        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {

            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
                if (total < 0) { // total unknown
                    System.err.println(future.channel() + " Transfer progress: " + progress);
                } else {
                    System.err.println(future.channel() + " Transfer progress: " + progress + " / " + total);
                }
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) {
                System.err.println(future.channel() + " Transfer complete.");
            }
        });

        // 如果使用 Chunked 编码，最后则需要发送一个编码结束的空消息体，进行标记，表示所有消息体已经成功发送完成
        ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

        // 如果当前连接请求非 keep-alive，最后一包消息发送完成后，服务器主动关闭连接
        if (!HttpUtil.isKeepAlive(request)) {
            // Close the connection when the whole content is written out.
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }

    private void setDateAndCacheHeaders(HttpResponse response, File fileToCache) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));

        // Date header
        Calendar time = new GregorianCalendar();
        response.headers().set(DATE, dateFormatter.format(time.getTime()));

        // Add cache headers
        time.add(Calendar.SECOND, HTTP_CACHE_SECONDS);
        response.headers().set(EXPIRES, dateFormatter.format(time.getTime()));
        response.headers().set(CACHE_CONTROL, "private, max-age=" + HTTP_CACHE_SECONDS);
        response.headers().set(LAST_MODIFIED, dateFormatter.format(new Date(fileToCache.lastModified())));
    }

    private void sendNotModified(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, NOT_MODIFIED);
        setDateHeader(response);

        // Close the connection as soon as the error message is sent.
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    // 重定向操作
    private static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
        // 建立响应对象
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
        // 设置新的请求地址放入响应对象中去
        response.headers().set(LOCATION, newUri);
        // 使用 ctx 对象写出并刷新到 SocketChannel 中去，并主动关闭连接（这里指处理发送数据的线程连接）
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    // web服务器可以跳转一个 Controller，遍历文件并跳转至
    private void sendListing(ChannelHandlerContext ctx, File dir) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");

        StringBuilder buf = new StringBuilder();
        String dirPath = dir.getPath();

        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        buf.append("Listing of: ");
        buf.append(dirPath);
        buf.append("</title></head><body>\r\n");

        buf.append("<h3>Listing of: ");
        buf.append(dirPath);
        buf.append("</h3>\r\n");

        buf.append("<ul>");
        buf.append("<li><a href=\"../\">..</a></li>\r\n");
        File[] files = dir.listFiles();
        if (null != files && files.length > 0) {
            for (File f : files) {
                if (f.isHidden() || !f.canRead()) {
                    continue;
                }

                String name = f.getName();
                if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                    continue;
                }

                buf.append("<li><a href=\"");
                buf.append(name);
                buf.append("\">");
                buf.append(name);
                buf.append("</a></li>\r\n");
            }
        }

        buf.append("</ul></body></html>\r\n");
        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();

        // Close the connection as soon as the error message is sent.
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private String sanitizeUri(String uri) {
        try {

            // 使用 UTF-8 字符集
            uri = URLDecoder.decode(uri, CharsetUtil.UTF_8.displayName());
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, CharsetUtil.ISO_8859_1.displayName());
            } catch (UnsupportedEncodingException e1) {
                // 抛出预想的异常信息
                throw new Error();
            }
        }
        // 对 uri 进行细粒度判断：4步验证操作
        // 1. 基础验证
        if (!uri.startsWith(url)) {
            return null;
        }
        // 2.
        if (!uri.startsWith("/")) {
            return null;
        }
        // 3. 将文件分隔符替换为本地操作系统的文件路径分隔符
        uri = uri.replace('/', File.separatorChar);
        // 4. 二次验证合法性
        if (uri.contains(File.separator + ".") || uri.contains("." + File.separator) || uri.startsWith(".")
            || uri.endsWith(".") || INSECURE_URI.matcher(uri).matches()) {
            return null;
        }
        // 当前工程所在目录 + URI 构造绝对路径进行返回
        return System.getProperty("user.dir") + File.separator + uri;
    }

    private static void setContentTypeHeader(HttpResponse response, File file) {
        // 使用 mime对象获取文件类型
        MimetypesFileTypeMap map = new MimetypesFileTypeMap();
        response.headers().set(CONTENT_TYPE, map.getContentType(file.getPath()));
    }

    // 错误信息
    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Failure :" + status.toString() + "\r\n", CharsetUtil.UTF_8);
        // 建立响应对象
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, byteBuf);
        // 设置响应头信息
        response.headers().set(CONTENT_TYPE, "text/plain;charset=UTF-8");
        // 使用 ctx 对象写出并刷新到 SocketChannel 中去，并主动关闭连接（这里指处理发送数据的线程连接）
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static void setDateHeader(FullHttpResponse response) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));

        Calendar time = new GregorianCalendar();
        response.headers().set(DATE, dateFormatter.format(time.getTime()));
    }
}
