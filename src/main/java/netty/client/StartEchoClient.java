package netty.client;

public class StartEchoClient {

	public static void main(String[] args) throws InterruptedException {
		ClientBootForEcho b = new ClientBootForEcho(new ChannelInitForEchoClient());
		b.start();
	}
}
