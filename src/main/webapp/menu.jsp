<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<link rel="stylesheet" href="css/Menu.css" type="text/css" />
<%@ include file ="login-check.jsp" %>
<body>
	<header class="header">
    	<p class="system">タスク管理システム</p>
    	<p class="name">4167 SYSTEM</p>
    </header>
    <main class="main">
		<h1>MENU</h1>
		<p>ようこそ！<%=session.getAttribute("userName") %>さん</p>
		
		<form action="TaskAddServlet" method="get">
			<button type="submit">タスク登録</button>	
		</form>
		<form action="TaskListServlet" method="get">
			<button type="submit">タスク一覧表示</button>
		</form>
		<form action="logout-success.jsp" method="post">
			<button type="submit">ログアウト</button>
		</form>
	</main>
</body>
</html>