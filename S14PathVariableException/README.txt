[날짜 값 변환, @PathVariable, 익셉션 처리]

1. @DateTimeFormat
2. @PathVariable
3. 익셉션(Exception) 처리

(애너테이션)
1. @ControllerAdvice("spring")
2. @ExceptionHandler(RuntimeException.class)
3. @DateTimeFormat(pattern = "yyyyMMddHH")
4. @GetMapping("/members/{id}")
5. @PathVariable("id")
--------------------------------------------------
@DateTimeFormat(pattern = "yyyyMMddHH")
1. 자동으로 Validator를 처리한다. 즉 Validator를 만들지 않아도 된다.
2. label.properties에 아래와 같이 등록하면 에러 메시지를 출력한다.(반드시 메시지 코드를 등록해야 한다.)
	- typeMismatch.java.time.LocalDateTime=잘못된 형식
3. 컨트롤러에서 Validator 처리 : 파라미터에 Errors errors 지정 
		@RequestMapping("/members")
		public String list(
				@ModelAttribute("cmd") ListCommand listCommand,	Errors errors, Model model) {
			if (errors.hasErrors()) {
				return "member/memberList";
			}
			...
	}
--------------------------------------------------
(실행)
 - http://localhost:8584/S14PathVariableException/members
 - from : yyyyMMddHH, 2023040110
 - 	to	: yyyyMMddHH, 2023043012
 - 소스 : MemberListController.java, ListCommand.java, memberList.jsp

---------------------------------------------------
(사용자 태그 라이브러리)
1. memberList.jsp
	- <%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
	- <tf:formatDateTime value="${mem.registerDateTime }" pattern="yyyy-MM-dd" />
2. WEB-INF/tags/formatDateTime.tag
	<%@ tag body-content="empty" pageEncoding="utf-8" %>
	<%@ tag import="java.time.format.DateTimeFormatter" %>
	<%@ tag trimDirectiveWhitespaces="true" %>
	<%@ attribute name="value" required="true" type="java.time.temporal.TemporalAccessor" %>
	<%@ attribute name="pattern" type="java.lang.String" %>
	<%
		if (pattern == null) pattern = "yyyy-MM-dd";
	%>
	<%= DateTimeFormatter.ofPattern(pattern).format(value) %>

-----------------------------------------------------
(@PathVariable)
1. URL 요청 주소에 변수형태로 가변 경로를 지정
2. 일반적인 방식
	- http://localhost:8584/S14PathVariableException/members?id=10
3. PathVariable 방식
	- http://localhost:8584/S14PathVariableException/members/10
4. 예제 컨트롤러 : MemberDetailController.java
	@GetMapping("/members/{id}")
	public String detail(@PathVariable("id") Long memId, Model model) {
		...
	}
5. 예제 
	- http://localhost:8584/S14PathVariableException/members
		맴버 리스트에서 맴버 이메일을 클릭하면 아래
	<a href="<c:url value="/members/${mem.id}"/>">${mem.email}</a>
	  PathWariable 형태로 요청하여 상세 정보로 이동
	- http://localhost:8584/S14PathVariableException/members/{id}	

-----------------------------------------------------
(@ExceptionHandler)
1. 예외가 발생하면 처리할 메소드를 지정
2. 해당 메소드에서 뷰폼으로 이동하도록 처리
3. 예제 : MemberDetialController.java
	- 해당하는 맴버가 없으면 noMember.jsp 페이지로 이동
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleNotFoundException() {
		return "member/noMember";
	}
	
-----------------------------------------------------
(@ControllerAdvice)
1. 공통 익셉션 처리
2. CommonExceptionHandler.java
	- 패키지(spring)의 컨트롤러에서 발생되는 모든 예외를 처리
	- RuntimeException : 실행시간 예외
	
	@ControllerAdvice("spring")
	public class CommonExceptionHandler{
		@ExceptionHandler(RuntiomeException.class)
		public String handleRuntimeException(){
			return "error/commonException";
			}
		}
3. 공통 예외 페이지
	- commonException.jsp