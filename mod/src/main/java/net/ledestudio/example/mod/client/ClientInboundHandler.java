package net.ledestudio.example.mod.client;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
//import net.ledestudio.example.common.data.User;
import net.ledestudio.example.mod.data.User;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.logging.Logger;

public class ClientInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Map<String, Channel> channels = Maps.newConcurrentMap();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Logger.getLogger("Network").info("Mod Channel Active");

        ctx.channel();
        ctx.channel().remoteAddress().toString();


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
//        ((ByteBuf) msg).release();
        try {
            ByteBuf buf = (ByteBuf) msg;
            final int id = buf.readInt();
            if (id == 1) {
                User user = new User(buf);
                Logger.getLogger("NetWork Client add text").info(user.toString());
            }

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
