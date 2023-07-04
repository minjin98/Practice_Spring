[SMFBoards23]

------------------------------------------------------------------
(프로젝트 생성)
1. Maven Project
	- SMFBoards23
	- war(Web Application Archive)
2. pom.xml
	- spring-webmvc
	- javax.servlet-api
	- javax.servlet.jsp-api
	- jstl
3. web.xml
	- 패스: src/main/webapp/WEB-INF
4. 빈설정 : MvcConfig.java
	- @Configuration
	- @EnableWebMvc

	
------------------------------------------------------------------
[실행]
(hello) 
http://localhost:8584/SMFBoards23/index.html
http://localhost:8584/SMFBoards23/helloServlet
http://localhost:8584/SMFBoards23/hello/hello?name=kim
http://localhost:8584/SMFBoards23/hello/hello
http://localhost:8584/SMFBoards23/hello/hello2?name=Kim

(게시판)
http://localhost:8584/SMFBoards23/boards/

	