[요청 매핑, 커맨드 객체, 리다이렉트, 폼 태그, 모델]

1. @ReaquestMapping 설정
2. 요청 파라미터 접근
3. 리다이렉트
4. 개발환경 구축
5. 스프링 폼 태그
6. 모델 처리
----------------------------------------------------------------------------------
(실행순서)
1. 시작
	- http://localhost:8584/S11RequestMapping/home.do
	- http://localhost:8584/S11RequestMapping/main
	
2. 회원가입 : 폼(Form)
	- http://localhost:8584/S11RequestMapping/register/step1
	
3. 회원등록(요청처리) : RegisterContorller.java
	- modelAttribute를 사용하기 위해서는 RegisterRequest 객체를 생성해서
		model.attribute("registerRequest", registerRequest); 형태로 전달
	- http://localhost:8584/S11RequestMapping/register/step2
	- http://localhost:8584/S11RequestMapping/register/step2EL
	
4. 회원정보 표시(뷰) : JSP, modelAttribute = "registerRequest"
	- http://localhost:8584/S11RequestMapping/register/step2.jsp
	- http://localhost:8584/S11RequestMapping/register/step2EL.jsp
----------------------------------------------------------------------------------
(실행)
http://localhost:8584/S11RequestMapping/
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			//registry.addViewController("/main").setViewName("main");
			registry.addViewController("/").setViewName("main");
}
----------------------------------------------------------------------------------
(실행)
1. 컨트롤러 구현 없는 경로 매핑
	- 컨트롤러가 없어도 /main으로 요청하면 뷰(main.jsp)를 보여준다.
2. 예 : http://localhost:8584/S11RequestMapping/main
3. 코드 : MvcConfig.java
	@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
}
4. 컨트롤러를 통한 매핑
	- http://localhost:8584/S11RequestMapping/home
	- HomeController.java
	- 뷰(main.jsp)로 연결
---------------------------------------------------------------------------------
(Home 실행)
1. http://localhost:8584/S11RequestMapping/home
2. 설정 : ControllerConfig.java
	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
3. 자바소스 : HomeController.java

@Controller
	public class HomeController {
		@RequestMapping("/home")
		public String home() {
				return "home";	// home.jsp
		}
	}
4. 웹(뷰) 소스 : webapp/WEB-INF/view/home.jsp
---------------------------------------------------------------------------------
(Survey 실행)
1. 시작 및 요청 : SurveyController.java
	- http://localhost:8584/S11RequestMapping/survey
	- GET 방식 : SurveyController::form
2. 설문조사 : 폼(JSP), List<Question> 객체 전달
	- survey/surveyForm.jsp