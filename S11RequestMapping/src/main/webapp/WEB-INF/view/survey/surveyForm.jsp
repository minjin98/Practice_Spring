<!--동적 처리 -->
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>설문조사</title>
</head>
<body>
    <h2>설문조사</h2>
    <form method="post">
    <c:forEach var="q" items="${questions}" varStatus="status">
    <p>
    	<!-- 당신의 역할과 개발도구 -->
        ${status.index + 1}. ${q.title}<br/>
        <c:if test="${q.choice}">
            <c:forEach var="option" items="${q.options}">
            <label>
            	<input type="radio"  name="responses[${status.index}]" value="${option}">
                ${option}
                </label>
            </c:forEach>
        </c:if>
        
        <!-- 하고 싶은 말을 적어주세요 -->
        <c:if test="${! q.choice }">
        <input type="text" name="responses[${status.index}]">
        </c:if>
    </p>
    </c:forEach>

    <p>
        <label>응답자 위치:<br>
        <input type="text" name="res.location">
        </label>
    </p>
    <p>
        <label>응답자 나이:<br>
        <input type="text" name="res.age">
        </label>
    </p>
    <input type="submit" value="전송">
    </form>
</body>
</html>
