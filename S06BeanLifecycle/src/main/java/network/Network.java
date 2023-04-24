package network;

public class Network {
	private String host;
	
	public boolean connect(String host) {
		this.host = host;
		System.out.printf("Network.connect(%s)\n", host);
		return true;
	}
	public int send(String msg) {
		System.out.printf("Network.send(%s)\n", msg);
		return msg.length();
	}
	public boolean disconnect() {
		System.out.printf("Network.disconnect(%s)\n", this.host);
		return true;
	}
}
