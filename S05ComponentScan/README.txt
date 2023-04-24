[S05ComponentScan]

1. 컴포넌트 스캔
2. @ComponentScan
	- 컴포넌트 스캔 설정
3. 컴포넌트 스캔 대상
	- @Component
	- @Controller
	- @Service
	- @Repository 
	- @Aspect
	- @Configuration
(@ComponentScan)
1. 지정된 패키지와 그 하위 패키지에 속한 클래스를 스캔 대상
2. 스캔 대상에 해당하는 클래스 중에서 @Component 애너테이션이 붙은 
	클래스의 객체를 생성해서 빈으로 등록
3. @Configuration 애너테이션 있는 클래스에 함께 지정
4. 예 :
	- ComponentScan(basePackages = {"package"})
	- ComponentScan(basePackages = {"package;package"})
	- ComponentScan(basePackages = {"package", "package"})
	- ComponentScan(basePackages = {"Class1.class, Class2.class"})
	
(@Component)
1. 스캔 대상이 되는 클래스로서 빈객체로 등록
2. 빈객체 이름을 지정하지 않으면 클래스 이름에서 
	첫문자를 소문자로 변경해서 클래스 이름으로 등록
	- 예1 : 아래 Component 클래스의 빈객체 이름은 "component"가 된다.
	@Component
	public class Component{
	}
3. 명시적으로 빈객체 이름을 지정
	- 예2 : 아래 Component 클래스의 빈객체 이름은 "comp"가 된다.
	@Component("comp")
	public class Component{
	}
	
(MainForSpring.java)

(ManualBean.java)
package config;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ManualBean {
}
