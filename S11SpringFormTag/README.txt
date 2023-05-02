[Spring Form Tag]

1. HTML의 <form> 태크를 위한 커스텀 태그 : <form:form>
2. <%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
3. method 속성을 지정하지 않으면 : "POST"
4. action 속성을 지정하지 않으면 : 현재 요청 URL
5. <input> 커스텀 태그
	- <form:input>
	- <form:password>
	- <form:hidden>
6. <select> 커스텀 태그
	- <form:select>
	- <form:options>
	- <form:option>
7. <checkbox> 커스텀 태그
	- <form:checkboxes>
	- <form:checkbox>
8. <radio> 커스텀 태그
	- <form:radiobuttons>
	- <form:radiobutton>
9. <textarea> 커스텀 태그
	- <form:textarea>
10. <form:form> 커스텀 태그와 HTML 일반 태그 혼용 가능

------------------------------------------------------------------------------
(스프링 폼 태그에서 ModelAttribute 사용하기)
@ModelAttribute("formCommand")
public FormCommand createCommand() {
	return new FormCommand();
}

@GetMapping
public String form(Model model,FormCommand formCmd) {
}

------------------------------------------------------------------------------
(실행)

1. http://localhost:8584/S11SpringFormTag/form
2. 요청 : FormController.java
	- @ModelAttribute("formCommand") public FormCommand createCommand() 실행이 되어
	  요청처리 메소드인 form(...)에 FormCommand 파라미터로 객체를 전달
	- @GetMapping
	  public String form(Model model,FormCommand formCmd)
3. 로그인 뷰 : 
	- WEB-INF/view/form.jsp
	
--------------------------------------------------------------------------------
(form.jsp)
<form:select path="loginType" items="${loginTypes}" />

<select id="loginType" name="loginType">
	<option value="일반회원">일반회원</option>
	<option value="기업회원" selected="selected">기업회원</option>
	<option value="헤드헌터회원">헤드헌터회원</option>
</select>

..........................................................
<form:select path="loginType">
	<option value="">--- 선택하세요 ---</option>
	<form:options items="${loginTypes}" />
</form:select>
			
<select id="loginType" name="loginType">
	<option value="">--- 선택하세요 ---</option>
	<option value="일반회원">일반회원</option>
	<option value="기업회원" selected="selected">기업회원</option>
	<option value="헤드헌터회원">헤드헌터회원</option>
</select>

..........................................................
<form:select path="jobCode">
	<option value="">--- 선택하세요 ---</option>
	<form:options items="${jobCodes}" itemLabel="label" itemValue="code" />
</form:select>

<select id="jobCode" name="jobCode">
	<option value="">--- 선택하세요 ---</option>
	<option value="01">프로그래머</option>
	<option value="02">디자이너</option>
</select>	
		