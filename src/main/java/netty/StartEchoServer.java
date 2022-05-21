package netty;

public class StartEchoServer {

	public static void main(String[] args) throws InterruptedException {
		EchoServerBootStrap b = new EchoServerBootStrap(8081, new ChannelInitForEcho());
		b.start();
	}
}
