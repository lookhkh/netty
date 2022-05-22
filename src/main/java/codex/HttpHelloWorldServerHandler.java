package codex;

import java.util.concurrent.Callable;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class HttpHelloWorldServerHandler extends ChannelInboundHandlerAdapter {
	private static final byte[] CONTENT = "HELLOW WORLD".getBytes(CharsetUtil.UTF_8);
	private static final AsciiString CONTENT_TYPE = new AsciiString("content-Type");
	private static final AsciiString CONTENT_LENGTH = new AsciiString("content-Length");
	private static final AsciiString CONNECTION = new AsciiString("Connection");
	private static final AsciiString KEEP_ALIVE = new AsciiString("keep-alive");


	
	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof HttpRequest) {
			final HttpRequest req = (HttpRequest)msg;
			
			if(HttpHeaders.is100ContinueExpected(req)) {
				ctx.write(new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
			}
			
			Future<byte[]> result = ctx.executor().submit(new Callable<byte[]>() {
				 
				public byte[] call() throws Exception {
					Thread.sleep(1000);
					return "HeLLO World Today is my best day of all because of you".getBytes(CharsetUtil.UTF_8);
				}
			});
			
			result.addListener(new GenericFutureListener<Future<? super byte[]>>() {
				public void operationComplete(Future<? super byte[]> future) throws Exception {
			
					byte[] result = (byte[]) future.get();
					boolean keepAlive = HttpHeaders.isKeepAlive(req);
					FullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(result));
					res.headers().set(CONTENT_LENGTH,res.content().readableBytes());
					res.headers().set(CONTENT_TYPE,"text/plain");
					
					if(!keepAlive) {
						ctx.write(res).addListener(ChannelFutureListener.CLOSE);
					}else {
						res.headers().set(CONNECTION,KEEP_ALIVE);
						ctx.write(res);
					}			
					
					ctx.flush();
				}
			
			});
			
			
		}else {
			throw new IllegalArgumentException("HTTP_FORMAT¿Ã æ∆¥’¥œ¥Ÿ.");
		}
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}

}
