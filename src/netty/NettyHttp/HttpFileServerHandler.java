package netty.NettyHttp;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by Yue on 2015/7/14.
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

    private String uri;

    public HttpFileServerHandler(String uri){
        this.uri=uri;
    }


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

    }



}
