package netty.WebSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * Created by Yue on 2015/7/15.
 */
public class WebSocketServer {

    public static void main(String[] args) {
        int port=8080;
        new WebSocketServer().run(port);
    }

    public void run(int port) {

        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGrop=new NioEventLoopGroup();

        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-codec",new HttpServerCodec());
                            ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                            ch.pipeline().addLast(new WebServerSocketHander());
                        }
                    });
            Channel channel = b.bind(port).sync().channel();
            System.out.println("Web socket started at "+port);
            System.out.println("Open your browser to navigate http://localhost:"+port);
            channel.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGrop.shutdownGracefully();
        }


    }


}
