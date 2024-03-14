<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト｜4167SYSTEM</title>
</head>
<link rel="stylesheet" href="css/Logout.css" type="text/css" />
<body>
	<header class="header">
    	<p class="system">タスク管理システム</p>
    	<p class="name">4167 SYSTEM</p>
    </header>
    <main class="main">
		<h1>LOGOUT</h1>
		<h3>ログアウトしました。</h3>
		<%
			session.invalidate();
		%>
		<form action="login.jsp" method="POST">
			<button type="submit">ログイン</button>
		</form>
	</main>
</body>
</html>