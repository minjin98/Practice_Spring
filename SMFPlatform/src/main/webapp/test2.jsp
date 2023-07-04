<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*,config.db.*" %>
<%@ page import="controller.process.*" %>
<jsp:useBean id="regMgr" class="controller.process.RegisterMgr" />

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
	   <td><strong>NAME</strong></td>
	   <td><strong>Good_count</strong></td>
	   <td><strong>Bad_count</strong></td>
	   <td><strong>Issue_count</strong></td>
	</tr>
	
	<%
		Vector<ProcessBean> vlist = regMgr.getRegisterList();
		int counter = vlist.size();
		for(int i=0; i<vlist.size(); i++){
			ProcessBean regBean =vlist.get(i);
	%>
	<tr>
		<td><%=regBean.getProdName()%></td>
		<td><%=regBean.getGood_count()%></td>
		<td><%=regBean.getBad_count()%></td>
		<td><%=regBean.getIssue_count()%></td>
		
	</tr>
	<%
	   }
	%>
	</table>
	<br/>
	<br/>
	total records : <%= counter %> 
</body>
</html>