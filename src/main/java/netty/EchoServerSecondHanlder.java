package netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

@Sharable
public class EchoServerSecondHanlder extends ChannelInboundHandlerAdapter {


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		HttpRequest decodedMsg = (HttpRequest)msg;
		System.out.println("decodedMsg : "+decodedMsg);     
		ctx.write(ctx.alloc().buffer().writeBytes(decodedMsg.getDecoderResult().toString().getBytes());
	}	
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("read completed");
		ctx.flush();
	}



}
