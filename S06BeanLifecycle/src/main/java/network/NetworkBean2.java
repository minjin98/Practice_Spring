/*
 * 가정 : 라이브러리 형태로 제공되어 수정할 수 없다. 
 */
package network;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkBean2 extends Network  implements InitializingBean, DisposableBean {
	
	Network network = new Network();
	
	public void open() {
		System.out.println("NetworkBean2 open()");
		super.connect("http://ez.edu");
	}
	public void close() {
		System.out.println("NetworkBean2 close()");
		super.disconnect();
	}
	
	@Override
	public int send(String msg) {
		System.out.println("NetworkBean2 send()");
		int len = super.send(msg);
		System.out.printf("msg len(%d) succeed.\n", len);
		return len;
	}
	@Override
	public void destroy() throws Exception {
		close();
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		open();
	}
	
}
