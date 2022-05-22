package netty;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;

import org.junit.Test;

import codex.HttpHelloWorldServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

public class HttpHandlerTest {

	final String charset = "UTF-8";
	
	@Test
	public void writeNoHttpFormat_Nothing_Happend() {
		
		final EmbeddedChannel ch = InitEmbedChannel();
		
		final ByteBuf buf = Unpooled.buffer();
		String inBoundMSg = "GET / HTTP1.1";
		buf.writeBytes(inBoundMSg.getBytes(Charset.forName(charset)));
		
				assertTrue(ch.isOpen());
				ch.writeInbound(buf);
				assertTrue(!ch.isOpen());
		
		//assertTrue(ch.writeInbound(buf));
		
		ByteBuf readBuf = (ByteBuf) ch.readInbound();
		assertNull(readBuf);
	}

	
	
	@Test
	public void writeHTTPFORMAT_RETURN_MSG() {
		EmbeddedChannel ch = InitEmbedChannel();
		
		ByteBuf buf = Unpooled.buffer();
		String inBoundMSg = "GET / HTTP1.1";
		
		HttpRequest req = 
				new DefaultFullHttpRequest
					(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
		
		ch.writeInbound(req);
		
		Object result = ch.readOutbound();
		
		assertNotNull(result);
		
		assertTrue(result instanceof FullHttpResponse);
		
		FullHttpResponse res = (FullHttpResponse)result;
		
		assertTrue(res.content().toString(Charset.forName(charset)).equals("HeLLO World Today is my best day of all because of you"));
		

	}
	
	private EmbeddedChannel InitEmbedChannel() {
	
		
		EmbeddedChannel ch = new EmbeddedChannel(new HttpHelloWorldServerHandler());
		return ch;
	}
}
