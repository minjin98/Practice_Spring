package singleton;

public class AppContext {
	private static Greeter greeter = null;
	
	public Greeter greeter() {
		if(greeter != null) {
			return greeter;
		}
		
		greeter = new Greeter();
		greeter.setFormat("%s, 안녕하세요!");
		return greeter;
		}
}

