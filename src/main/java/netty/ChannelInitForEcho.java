package netty;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;

public class ChannelInitForEcho extends ChannelInitializer<Channel> {

	private final ChannelHandler handler = new EchoServerHandler();
	private final ChannelHandler secondHan = new EchoServerSecondHanlder();
	

	
	protected void initChannel(Channel ch) throws Exception {
		
		
		ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO))
					.addLast(new HttpServerCodec())
					.addLast(handler).addLast(secondHan);
	};
}
