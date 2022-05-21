package ab;


import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public abstract class AbstractNettyBootStrapForClient {

	private final ChannelInitializer<Channel> ch;

	public AbstractNettyBootStrapForClient(ChannelInitializer<Channel> ch) {
		super();
		this.ch = ch;
	}


	public void start() throws InterruptedException {
		EventLoopGroup boss = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(boss)
			 .channel(NioSocketChannel.class)
			 .handler(this.ch);
			
			ChannelFuture f = b.connect("localhost",8081).sync();
			f.channel().closeFuture().sync();
			
		}finally {
			boss.shutdownGracefully();
		}
	}
	

}
