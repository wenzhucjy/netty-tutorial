package com.netty.hearbeat;

import com.netty.RequestInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * description: Client 心跳检测
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-02 16:27
 */
public class ClientHeartBeatHandler extends SimpleChannelInboundHandler {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> heartBeat;
    // 主动向服务器发送认证信息
    private InetAddress address;

    public static final String SUCCESS_KEY = "auth_success_key";


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            if (msg instanceof String) {
                String ret = (String) msg;
                if (SUCCESS_KEY.equals(ret)) {

                    // 握手成功，主动发送心跳信息，每隔2s不延迟发送
                    this.heartBeat = this.scheduler.scheduleWithFixedDelay(
                            new HeartBeatTask(ctx), 0, 2, TimeUnit.SECONDS
                    );
                }
                System.out.println(msg);
            }

        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        String key = "1234"; // 认证的唯一key
        String auth = ip + "," + key;// 生产环境建议加密算法
        ctx.writeAndFlush(auth);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);

    }

    // 发送心跳的任务
    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        private HeartBeatTask( ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            // 用sigar收集客户端的信息，解压sigar.jar，拷贝对应的文件到JAVA_HOME/bin 目录下
            RequestInfo info = new RequestInfo();
            info.setIp(address.getHostAddress());
            Sigar sigar = new Sigar();
            try {
                CpuPerc cpuPerc = sigar.getCpuPerc();
                Map<String, Object> cpuPercMap = new HashMap<>();
                cpuPercMap.put("combined", cpuPerc.getCombined());
                cpuPercMap.put("user", cpuPerc.getUser());
                cpuPercMap.put("sys", cpuPerc.getSys());
                cpuPercMap.put("wait", cpuPerc.getWait());
                cpuPercMap.put("idle", cpuPerc.getIdle());
                info.setCpuPercMap(cpuPercMap);

                Map<String, Object> memoryMap = new HashMap<>();
                Mem mem = sigar.getMem();
                memoryMap.put("total", mem.getTotal() / 1024L);
                memoryMap.put("used", mem.getUsed() / 1024L);
                memoryMap.put("free", mem.getFree() / 1024L);
                info.setMemoryMap(memoryMap);

                ctx.writeAndFlush(info);
            } catch (SigarException e) {
                e.printStackTrace();
            }
        }
    }

}
