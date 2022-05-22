package codex;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class OutBound extends ChannelOutboundHandlerAdapter {


	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("Write!!! ");
		System.out.println(msg);
		
		ctx.writeAndFlush(msg);
	}
	
}
