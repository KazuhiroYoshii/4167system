<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン判定</title>
</head>
<body>
	<%
	//セッションスコープからユーザーIDを取得
	String userId = (String)session.getAttribute("userId");
	
	//ユーザーIDがnullの場合はログアウト状態と判定、ログイン画面へ遷移させる
	if(userId == null){
		response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>