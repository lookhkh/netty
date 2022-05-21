package netty;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.HttpRequest;

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg);
		HttpRequest readMsg = (HttpRequest)msg;
		
		System.out.println("수신 문자열 "+readMsg);
		
		ctx.fireChannelRead(readMsg);
		
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
}
