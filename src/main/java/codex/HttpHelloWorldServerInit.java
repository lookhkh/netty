package codex;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class HttpHelloWorldServerInit extends ChannelInitializer<Channel> {


	protected void initChannel(Channel ch) throws Exception {
		ch.pipeline()
		.addLast(new LoggingHandler(LogLevel.INFO))
		.addLast(new ReadTimeoutHandler(10))
		.addLast(new HttpServerCodec())
		.addLast(new OutBound())
		.addLast(new HttpHelloWorldServerHandler());
	};
}
