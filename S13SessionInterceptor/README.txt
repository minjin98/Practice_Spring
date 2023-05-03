[세션, 인터셉터, 쿠키]

1. HttpSession
2. HandlerInterceptor
3. Cookie(쿠키) 접근

(실행)
http://localhost:8584/S13SessionInterceptor
http://localhost:8584/S13SessionInterceptor/main

(쿠키)
1. 쿠키생성 : 
	- Cookie 변수 =	new Cookie(이름, 값);
	- 예 : LoginController.java
		Cookie rememberCookie =	new Cookie("REMEMBER", loginCommand.getEmail());
				rememberCookie.setPath("/");
				if (loginCommand.isRememberEmail()) {
					rememberCookie.setMaxAge(60 * 60 * 24 * 30); // 유효기간 : 30일동안 쿠키를 보관
				} 
				else {
					rememberCookie.setMaxAge(0);	// 즉시 폐기
				}
				response.addCookie(rememberCookie); // 응답객체에 쿠키를 전달
		}
2. 쿠키받기 :  
	- 컨트롤러에서 @CookieValue(value = "이름", required = false) Cookie 쿠키변수
	- 예 : LoginController.java
			@GetMapping
		    public String form(LoginCommand loginCommand,
		    	@CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
				if (rCookie != null) {
					loginCommand.setEmail(rCookie.getValue());
					loginCommand.setRememberEmail(true);
				}
		    	return "login/loginForm";
		}
		
		
----------------------------------------------------------------------------------------------------
(인터셉터)
1. 가로채기 : 컨트롤러가 실행전,후 또는 뷰를 실행한 후 처리를 가로채어 방향을 바꾼다.
2. HandlerInterceptor 인터페이스
public interface HandlerInterceptor {
	// 컨트롤러 실행 전
	default boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, 
		Object handler);
		
	// 컨트롤러 실행 후 , 아직 뷰를 실행하기 전
	default void postHandle(HttpServletRequest request, 
		HttpServletResponse response, 
		Object handler,
		@Nullable ModelAndView modelAndView);
		
	// 뷰를 실행한 후
	default void afterCompletion(HttpServletRequest request, 
		HttpServletResponse response, 
		Object handler,
		@Nullable Exception ex);

3. AuthCheckInterceptor.java
	- http://localhost:8584/S13SessionInterceptor/edit/changePassword
	- 패스워드 변경을 위해 위의 URL로 요청을 하면 로그인 유무를 확인한다.
	- 로그인이 되어 있으면 "비밀번호변경" 페이지를 보여준다.
	- 로그인이 되어 있지 않으면 로그인(/login) 요청으로 보낸다.
4. 설정 : MvcConfig.java
	- WebMvcConfigurer의 구현 클래스에서 addInterceptors 메소드를 재정의 해야 한다.
		public void addInterceptors(InterceptorRegistry registry)	
	- HandlerInterceptor 인터페이스를 상속한 구현 클래스를 Bean으로 등록해야 한다.
	- 예 : MvcConfig.java

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor())
			.addPathPatterns("/edit/**")
			.excludePathPatterns("/edit/help/**");
	}

	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}

5. 패턴 : *, **, ?
	* 	: 0개 또는 그 이상의 글자
	? 	: 1개 글자
	**	: 0개 또는 그 이상의 폴더 경로
