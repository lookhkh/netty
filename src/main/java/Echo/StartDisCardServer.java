package Echo;

public class StartDisCardServer {

	public static void main(String[] args) throws InterruptedException {
		DIsCardServerBootStrap b = new DIsCardServerBootStrap(8080, new ChannelInitForDisCard());
		b.start();
	}
}
