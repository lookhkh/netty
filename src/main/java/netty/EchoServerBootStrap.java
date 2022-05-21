package netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class EchoServerBootStrap extends ab.AbstractNettyBootStrapServer {

	private final ChannelInitializer<Channel> ci;
	
	public EchoServerBootStrap(int pORT, ChannelInitializer<Channel> ch) {
		super(pORT);
		this.ci = ch;
	}
	
	public void start() throws InterruptedException {
		super.start(this.ci);
	}
	
	

	
	
}
