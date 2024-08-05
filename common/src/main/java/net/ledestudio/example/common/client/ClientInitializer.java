package net.ledestudio.example.common.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import net.ledestudio.example.common.server.ServerInboundHandler;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ServerInboundHandler());
    }
}
