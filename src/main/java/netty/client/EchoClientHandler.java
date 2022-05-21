package netty.client;

import java.util.Random;
import java.util.Scanner;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channel active!!! ");
		ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("GET / HTTP 1.1".getBytes()));
		
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		String readMsg = ((ByteBuf)msg).toString(CharsetUtil.UTF_8);
		
		System.out.println("수신 문자열 "+readMsg);
		
		System.out.println("야 클라이언트, 뭐 보낼래?");
		
		String result = "hi "+new Random().nextInt();
		
		System.out.println(result + "  =---- > input");
		ctx.writeAndFlush(Unpooled.copiedBuffer(result.getBytes(CharsetUtil.UTF_8)));
		
		
	}
	
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx+" is gotten inactive");
		super.channelInactive(ctx);
	}
}
