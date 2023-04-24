[S06BeanLifecycle]

1. 빈객체 생명주기 관리
	- 컨테이너 초기화와 종료
	- 빈객체의 생성
	- 의존 설정
	- 초기화
	- 소멸
2. 싱글톤과 프로토타입 범위


(InitializingBean 인터페이스)
- 빈객체를 생성한 뒤에 초기화 과정을 마치고 호출된다.
public interface InitializingBean {
	void afterPropertiesSet() throws Exception;
}

(DisposableBean 인터페이스)
- 빈객체가 소멸하기 직전에 호출된다.
public interface DisposableBean {
	void destroy() throws Exception;
}

(Client.java)
- 빈객체 생성과 소멸 시점에 특별하게  처리를 해야할 로직을 추가할 수 있다,
- afterPropertiesSet()는 초기화 과정에 필요한 로직 추가
- destroy() 소멸 과정에 필요한 로직 추가 
public class Client implements InitializingBean, DisposableBean {
@Override
	@Override	
	public void afterPropertiesSet() throws Exception {
	}
	
	@Override	
	public void destroy() throws Exception {
	}
-----------------------------------------------------------------
1.	빈객체 등록에서  애너테이션 속성을 이용하여 객체의 생성과 소멸시점에
	처리해야 할 필요한 로직을 적용할 수 있다.
2.	initMethod = "생성시점에 호출될 메소드 이름"
	destroyMethod = "소멸시점에 호출될 메소드 이름"
3. intitMethod와 desrtoyMethod에 정의되는 메소드의 파라미터는 없어야 한다.
4. 예 :
	@Bean(initMethod = "생성시점", destroyMethod = "소멸시점")
	public Client2 client2() {	
		return new client2();
	}
	public class Client2{
		public void 생성시점() { // 빈객체의 생성시점에 호출된다.
		}
		public void 소멸시점() { // 빈객체의 소멸시점에 호출된다.
		}
------------------------------------------------------------------
(빈객체의 생성과 관리 범위)
1. @Scope("singleton")
	- 기본값으로 지정하지 않으면 싱글톤
	- 하나의 빈객체에 대해서 하나의 객체를 공유
2. @Scope("prototype")
	- 빈객체를 구할 때 마다 새로운 빈객체를 생성
	- 완전한 라이프사이클을 따르지 않는다.
	- 빈객체의 생성하고 설정하고 초기화는 하지만 소멸은 처리하지 않는다.
	- 빈객체의 소멸에 대해서는 책임을 지지 않는다.