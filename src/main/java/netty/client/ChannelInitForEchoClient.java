package netty.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;

public class ChannelInitForEchoClient extends ChannelInitializer<Channel> {

	private final ChannelHandler handler = new EchoClientHandler();
	
	protected void initChannel(Channel ch) throws Exception {
		ch.pipeline().addLast(handler);
	};
}
