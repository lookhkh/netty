package netty;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

public class EchoServerOutBoundLoggingHandler extends ChannelOutboundHandlerAdapter {

	
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println(msg+" is outboud");
		ctx.write(ctx.alloc().buffer().writeBytes(((String)msg).getBytes()));

	}
	
	
}
