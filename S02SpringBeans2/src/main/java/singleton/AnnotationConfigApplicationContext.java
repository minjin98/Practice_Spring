package singleton;

public class AnnotationConfigApplicationContext {
	private AppContext context;
	public AnnotationConfigApplicationContext(AppContext context) {
		this.context = context;
	}
	public Object getBean(String bean) {
		return context.greeter();
	}
	public void close() {
		
	}
	
}
