package com.netty.hearbeat;

import com.netty.RequestInfo;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * description: Server 心跳检测
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-02 16:06
 */
public class ServerHeartBeatHandler extends SimpleChannelInboundHandler {

    private static Map<String, String> AUTH_IP_MAP = new HashMap<>();
    public static final String SUCCESS_KEY = "auth_success_key";

    static {
        AUTH_IP_MAP.put("192.168.56.1", "1234");// 从节点的IP及对应认证key
    }

    private boolean auth(ChannelHandlerContext ctx, Object msg) {
        // 生产环境先解密
        String[] ret = ((String) msg).split(",");
        String auth = AUTH_IP_MAP.get(ret[0]);
        if (null != auth && auth.equals(ret[1])) {
            ctx.writeAndFlush(SUCCESS_KEY);
            return true;
        } else {
            ctx.writeAndFlush("auth failure").addListener(ChannelFutureListener.CLOSE);
            return false;
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            auth(ctx, msg);
        } else if (msg instanceof RequestInfo) {
            RequestInfo info = (RequestInfo) msg;
            System.out.println("-------------------------------------------");
            System.out.println("IP：" + info.getIp());
            Map<String, Object> cpuMap = info.getCpuPercMap();
            System.out.println("CPU使用率：" + cpuMap.get("combined"));
            System.out.println("用户使用率：" + cpuMap.get("user"));
            System.out.println("系统使用率：" + cpuMap.get("sys"));
            System.out.println("等待率：" + cpuMap.get("wait"));
            System.out.println("空闲率：" + cpuMap.get("idle"));
            Map<String, Object> memMap = info.getMemoryMap();
            System.out.println("内存总量：" + memMap.get("total"));
            System.out.println("当前内存使用量：" + memMap.get("used"));
            System.out.println("当前内存剩余量：" + memMap.get("free"));
            ctx.writeAndFlush("info received");

        } else {
            ctx.writeAndFlush("connect failure!").addListener(ChannelFutureListener.CLOSE);
        }
    }
}
