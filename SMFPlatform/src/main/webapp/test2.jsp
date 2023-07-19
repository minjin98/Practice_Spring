<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*,controller.process.*" %>
<%@ page import="java.util.*,controller.process.ProcessBean" %>
<%@ page import="java.util.*,config.db.OracleDbConfig" %>
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
	   <td><strong>ProdNo</strong></td>
	   <td><strong>StartDate</strong></td>
	   <td><strong>EndDate</strong></td>
	   <td><strong>Name</strong></td>
	</tr>
	
	<%
		List<ProcessBean> results = proMgr.select_plan();
				int counter = results.size();
				for(int i=0; i<results.size(); i++){
			ProcessBean proBean =results.get(i);
		%>
	<tr>
		<td><%=proBean.getProdNo()%></td>
		<td><%=proBean.getStartDate()%></td>
		<td><%=proBean.getEndDate()%></td>
		<td><%=proBean.getName()%></td>
		
	</tr>
	<% }%>
	</table>
	<br/>
	<br/>
</body>
</html>