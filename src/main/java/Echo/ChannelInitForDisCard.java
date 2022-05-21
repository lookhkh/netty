package Echo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;

public class ChannelInitForDisCard extends ChannelInitializer<Channel> {

	private final ChannelHandler handler = new DisCardServerHandler();
	
	protected void initChannel(Channel ch) throws Exception {
		ch.pipeline().addLast(handler);
	};
}
