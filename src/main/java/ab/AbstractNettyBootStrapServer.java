package ab;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public abstract class AbstractNettyBootStrapServer {

	private final int PORT;
	
	public AbstractNettyBootStrapServer(int pORT) {
		PORT = pORT;
	}

	public void start(ChannelInitializer<Channel> ch) throws InterruptedException {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss,worker)
			 .channel(NioServerSocketChannel.class)
			 .handler(new ChannelInitializer<Channel>() {
				 @Override
				protected void initChannel(Channel ch) throws Exception {
					 ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
				}
			 })
			 .childHandler(ch);
			
			ChannelFuture f = b.bind(this.PORT).sync();
			f.channel().closeFuture().sync();
			
		}finally {
			worker.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}
	

}
