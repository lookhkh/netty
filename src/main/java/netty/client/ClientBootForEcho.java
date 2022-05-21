package netty.client;

import ab.AbstractNettyBootStrapForClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class ClientBootForEcho extends AbstractNettyBootStrapForClient {

	
	public ClientBootForEcho(ChannelInitializer<Channel> ch) {
		super(ch);
	}
	

	
}
