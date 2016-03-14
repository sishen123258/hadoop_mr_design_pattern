package netty.NettySerializable.client;

import NettySerializable.pojo.SubscribeReq;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Yue on 2015/7/14.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg = " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(resp(i));
        }
        ctx.flush();
    }

    private SubscribeReq resp(int i) {
        SubscribeReq req=new SubscribeReq();
        req.setAddress("ShangHai");
        req.setPhoneNumber("111111");
        req.setUserName("yuetong");
        req.setSubReqID(i);
        req.setProductName("Music");
        return req;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
