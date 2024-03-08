<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集失敗</title>
</head>
<body>
	<%@ include file="login-check.jsp" %>
	<h1>タスク編集失敗</h1>
	<hr>
	<h3>タスク情報を編集できませんでした。</h3><br>
	<form action="TaskListServlet" method="post">
		<input type="submit" value="一覧画面に戻る">
	</form>
</body>
</html>