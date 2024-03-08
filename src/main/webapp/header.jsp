<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヘッダー</title>
</head>
<body>
	<%
	String userId = (String)session.getAttribute("userId");
	String userName = (String)session.getAttribute("userName");
	if(userId == null){
		response.sendRedirect("login.jsp");
	}else{
	%>
	<h2><%=userName %>さん、こんにちは。</h2>
	<%
	}
	%>
</body>
</html>