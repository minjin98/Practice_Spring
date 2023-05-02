<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인</title>
</head>
<body>
    <p>환영합니다.</p>
    <p><a href="<c:url value="/global/register/step1" />">[회원 가입하기-글로벌 Validator]</a>
    <p><a href="<c:url value="/local/register/step1" />">[회원 가입하기-로컬 Validator]</a>
</body>
</html>
