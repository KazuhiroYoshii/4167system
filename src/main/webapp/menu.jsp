<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<%@ include file ="login-check.jsp" %>

	<h1>メニュー画面</h1>
	
	<b>ようこそ！<%=session.getAttribute("userName") %>さん</b>
	
	<hr>
	<form action="TaskAddServlet" method="get">
		<input type="submit" value="タスク登録"><br><br>	
	</form>
	<form action="TaskListServlet" method="get">
		<input type="submit" value="タスク一覧表示"><br><br>	
	</form>
	
	<form action="logout-success.jsp" method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>