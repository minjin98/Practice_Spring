<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,list.*"%>

<jsp:useBean id="regMgr" class="list.RegisterMgr"/>

<html>
<head>
	<title>JSP에서 데이터베이스 연동</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFCC">
	<h2>Bean를 사용한 데이터베이스 연동 예제</h2><br/>
	<h3>회원정보</h3>
	<table bordercolor="#0000ff" border="1">
	<tr>
	   <td><strong>IssueNo</strong></td>
	   <td><strong>IssueName</strong></td>
	   <td><strong>IssueInfo</strong></td>
	</tr>
	<%
	   Vector<IssueBean> vlist = regMgr.getRegisterList();
		int counter = vlist.size();
		for(int i = 0; i < vlist.size(); i++) {
	   		IssueBean regBean = vlist.get(i);
	%>
	<tr>
		<td><%=regBean.getIssueNo()%></td>
		<td><%=regBean.getIssueName()%></td>
		<td><%=regBean.getIssueInfo()%></td>
	</tr>
	<%}%>
	</table>
	<br/><br/>
	total records : <%= counter %> 
</body>
</html>