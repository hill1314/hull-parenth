package com.hull.test.nio.test1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过 netty 实现客户端请求
 *
 * @author
 * @create 2019-05-01 09:48
 **/
@Slf4j
public class EchoClient {

    public final String host;
    public final int port;
    public final int firstMessageSize;

    public EchoClient(String host,int port,int firstMessageSize) {
        this.host = host;
        this.port = port;
        this.firstMessageSize = firstMessageSize;
    }

    public void run(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler(firstMessageSize));
                        }
                    });

            // Start the client.
            ChannelFuture f = bootstrap.connect(host, port).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();

        }catch (Exception e){
            log.error("client error",e);
        }finally {
            group.shutdownGracefully();
        }

    }

}
