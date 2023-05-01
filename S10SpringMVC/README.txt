[스프링 MVC 프레임워크 동작 방식]

1. 스프링 MVC 구성 요소
2. DispatcherServlet
3. WebMvcConfigurer 스프링 MVC 설정

(스프링 MVC의 핵심 구성 요소)
1. 웹브라우저 : 요청 전송
2. DispatcherServlet : 창구 역할
3. HandlerMapping : 요청 URL과 매칭되는 컨트롤러 검색
4. HandlerAdaptor : 처리 요청
5. 컨트롤러(Controller) : 실행 및 결과 리턴
6. HandlerAdaptor  
	- 컨트롤러의 결과를 받은 후
	- 컨트롤러 실행 결과를 ModelAndView로 변환해서 리턴
7. DispatcherServlet  
	- ModelAndView를 받음
	- 컨트롤러 실행 결과를 보여줄 View 검색
	- ViewResolver
	- 응답 생성 요청
9. View
	- 응답 생성 : JSP
	- 해당 JSP를 웹브라우저 전송
--------------------------------------------------------------------
(실행)
http://localhost:8584/S10SpringMVC/hello?name=Hi

