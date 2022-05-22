package netty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.IllegalReferenceCountException;
import junit.framework.Assert.*;

public class ByteBufferT {

	@SuppressWarnings("deprecation")
	@Test
	public void readTest() {
		byte[] temp = {1,2,3,4,5,6,7,8,0,0};
		ByteBuffer firstBuf = ByteBuffer.wrap(temp);
		
		assertEquals(0, firstBuf.position());
		assertEquals(temp.length, firstBuf.limit());
		
		assertEquals(1, firstBuf.get());
		assertEquals(2, firstBuf.get());
		assertEquals(3, firstBuf.get());
		assertEquals(4, firstBuf.get());
		assertEquals(4, firstBuf.position());
		assertEquals(temp.length, firstBuf.limit());

		firstBuf.flip();
		
		assertNotSame(temp.length, firstBuf.limit());
		assertEquals(4, firstBuf.limit());

	}
	
	@Test
	public void byteBuf() {
		final ByteBuf buf = Unpooled.buffer();
		ByteBuf copied = buf.copy();
		buf.capacity(10);
		
		assertTrue(buf.isWritable(10));
		
		
		
		buf.writeBytes(new byte[] {1,2,3,4,5});
		assertTrue(buf.isWritable(5));
		
		
		buf.capacity(3);
		assertTrue(buf.isReadable(3));
		
		byte[] arr =buf.array();
		for(int i=0; i<arr.length; i++) {
			assertEquals(arr[i], i+1);
		}
		
		buf.release();
		assertThrows(IllegalReferenceCountException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				buf.release();
			}
		});
		
		System.out.println(copied);
		copied.release();

	}
}
