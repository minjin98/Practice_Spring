<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>
</head>
<body>
	<p>로그인 결과</p>
	<p>로그인 타입 : ${formCommand.loginType}</p>
	<p>직업 타입 : ${formCommand.jobCode}</p>
	<p>선호 OS</p>
	<ul>
		<c:forEach var ="os" items ="${formCommand.favoriteOs}" varStatus ="status">
			<li>${status.index+1}번 OS : ${os}</li> 
		</c:forEach>
	</ul>
</body>
</html>