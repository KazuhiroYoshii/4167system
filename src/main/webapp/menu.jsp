<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー｜4167 SYSTEM</title>
</head>
<link rel="stylesheet" href="css/Menu.css" type="text/css" />
<%@ include file ="login-check.jsp" %>
<%@ include file ="header.jsp" %>
<body>
    <main class="main">
		<h1>MENU</h1>
		<form action="TaskAddServlet" method="get">
			<button class="add" type="submit">タスク登録</button>	
		</form>
		<form action="TaskListServlet" method="get">
			<button class="list" type="submit">タスク一覧</button>
		</form>
		<form action="login.jsp" method="post">
			<button class="logout" type="submit">ログアウト</button>
		</form>
	</main>
</body>
</html>