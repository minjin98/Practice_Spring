<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*,controller.process.*" %>
<%@ page import="java.util.*,controller.process.ProcessBean" %>
<jsp:useBean id="proMgr" class="controller.process.ProcessDao" />

<html>
<head>
	<title>JSP에서 데이터베이스 연동</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
	<body bgcolor="#FFFFCC">
	<h2>Bean과 커넥션 풀을 사용한 데이터베이스 연동 예제</h2><br/>
	<h3>회원정보</h3>
	<table bordercolor="#0000ff" border="1">
	<tr>
	   <td><strong>1번라인</strong></td>
	</tr>
	
	<%
		String p1 = proMgr.selectOneLine();
	%>
	<tr>
		<td><%=p1%></td>
		
	</tr>
	</table>
	<br/>
	<br/>
</body>
</html>