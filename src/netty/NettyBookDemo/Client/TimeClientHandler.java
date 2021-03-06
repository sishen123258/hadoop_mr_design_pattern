package netty.NettyBookDemo.Client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Yue on 2015/7/10.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private final ByteBuf message;

    public TimeClientHandler() {

        byte[] bytes = "QUERY BY ORDER".getBytes();
        message = Unpooled.buffer(bytes.length);
        message.writeBytes(bytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("TimeClientHandler.exceptionCaught");
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("TimeClientHandler.channelRead");
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"UTF-8");
        System.out.println("Now is " + body);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("TimeClientHandler.channelActive");
        ctx.writeAndFlush(message);
    }
}
