<%@page contentType="application; charset=UTF-8"%>
<jsp:useBean id="bMgr" class="boards.dao.BoardDAO" />
<%
	  bMgr.downImage(request, response, out, pageContext);
%>