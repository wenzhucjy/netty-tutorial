package com.netty.client;

import com.chat.common.core.codec.RequestEncoder;
import com.chat.common.core.codec.ResponseDecoder;
import com.chat.common.core.model.Request;
import com.netty.client.swing.SwingClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * description: 客户端
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:32
 */
@Component
public class Client {

    @Autowired
    private SwingClient    swingClient;

    /**
     * 服务类
     */
    Bootstrap              bootstrap   = new Bootstrap();

    /**
     * 会话
     */
    private Channel        channel;

    /**
     * 线程池
     */
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        // 设置循环线程组事例
        bootstrap.group(workerGroup);

        // 设置channel工厂
        bootstrap.channel(NioSocketChannel.class);

        // 设置管道
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ResponseDecoder());
                ch.pipeline().addLast(new RequestEncoder());
                ch.pipeline().addLast(new ClientHandler(swingClient));
            }
        });
    }

    /**
     * 连接服务端
     * 
     * @throws InterruptedException
     */
    public void connect() throws InterruptedException {
        // 连接服务端
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 12121));
        connect.sync();
        channel = connect.channel();
    }

    /**
     * 关闭
     */
    public void shutdown() {
        workerGroup.shutdownGracefully();
    }

    /**
     * 获取会话
     *
     * @return Channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * 发送消息
     * 
     * @param request {@link Request}
     * @throws InterruptedException
     */
    public void sendRequest(Request request) throws InterruptedException {
        if (channel == null || !channel.isActive()) {
            connect();
        }
        channel.writeAndFlush(request);
    }
}
