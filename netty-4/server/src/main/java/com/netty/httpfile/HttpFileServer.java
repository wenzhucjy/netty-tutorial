package com.netty.httpfile;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * description: Netty 4 文件下载
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-05 11:02
 */
public class HttpFileServer {

    public static final String DEFAULT_URL = "/";

    public void run(int port, String url) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,
                    worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 加入http的解码器
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            // 假如 ObjectAggregator 解码器，作用是把多个消息转换为单一的FullHttpRequest 或 FullHttpResponse
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            // 加入 http 的解码器
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            // 加入 chunked，作用是支持异步发送的码流（大文件传输），但不占用过多的内存，防止 jdk 内存溢出
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            // 加入自定义处理文件服务器的业务逻辑 handler
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            System.out.println("HTTP文件目录服务器启动：" + "http://127.0.0.1:" + port + url);
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 9999;
        new HttpFileServer().run(port, DEFAULT_URL);
    }

}
