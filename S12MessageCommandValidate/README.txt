[MVC2: 메시지, 커맨드 객체 검증]

1. 메시지 처리
2. 커맨트 객체 검증과 에러 메시지

-----------------------------------------------------------
<spring:message> 태그

<spring:message code="..." arguments="..." />

<spring:message code="...">
	<spring:argument value="..." />
	<spring:argument value="..." />
</spring:message>

(step3.jsp)
register.done=<strong>{0}님 ({1})</strong>, 회원 가입을 완료했습니다.

<p>
	<spring:message code="register.done">
		<spring:argument value="${registerRequest.name}" />
		<spring:argument value="${registerRequest.email}" />
	</spring:message>
</p>
<p>        
	<spring:message code="register.done" arguments="${registerRequest.name},${registerRequest.email}"/>
</p>   
     
-----------------------------------------------------------
(메시지 파일 위치)
src/main/resources 폴더 참조
message.label : message 패키지 안에 label.properties 파일을 참조
메시지 파일 : *.properties
풀 패스 : src/main/resources/message/label.properties
자바 설정 : MvcConfig.java
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
-----------------------------------------------------------
(JSP에서 스프링 메시지 태그 처리를 위한 패키지)

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>	
	
<h2><spring:message code="term" /></h2>	

(label.properties)
term=약관

-----------------------------------------------------------
(메시지 언어별 처리)
기본: label.properties
영어: label_en.properties
한국: label_ko.properties

	
	
	