/*
 * 가정 : 라이브러리 형태로 제공되어 수정할 수 없다. 
 */
package network;

public class NetworkBean {
	
	Network network = new Network();
	
	public void open() {
		System.out.println("NetworkBean open()");
		network.connect("http://ez.edu");
	}
	public void close() {
		System.out.println("NetworkBean close()");
		network.disconnect();
	}
	public void send(String msg) {
		System.out.println("NetworkBean send()");
		int len = network.send(msg);
		System.out.printf("msg len(%d) succeed.\n", len);
	}
	
}
