package netty.FileServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by Yue on 2015/7/16.
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String>{

    public static final String CR=System.getProperty("line.separator");

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        File file=new File(msg);
        if(file.exists()){
            if(!file.isFile()){
                ctx.writeAndFlush("Not a File"+file+CR);
                return;
            }
            ctx.write(file + " length is" + file.length() + CR);
            RandomAccessFile randomAccessFile = new RandomAccessFile(msg, "r");
            FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
            ctx.write(region);
            ctx.write(CR);
            randomAccessFile.close();
        }else{
            ctx.writeAndFlush("File not exist"+file+CR);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
