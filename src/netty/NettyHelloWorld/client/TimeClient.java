package netty.NettyHelloWorld.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {

    public void connect(int port, String host) throws Exception {
        // ���ÿͻ���NIO�߳���
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel arg0)
                                throws Exception {
                            System.out.println("client initChannel..");
                            arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            arg0.pipeline().addLast(new StringDecoder());
                            arg0.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            // �����첽���Ӳ���
            ChannelFuture f = b.connect(host, port).sync();
            // �ȴ��ͻ�����·�ر�
            f.channel().closeFuture().sync();
        } finally {
            // �����˳����ͷ�NIO�߳���
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 9000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
            }
        }
        new TimeClient().connect(port, "127.0.0.1");
    }
}