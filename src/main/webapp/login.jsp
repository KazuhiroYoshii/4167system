<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<link rel="stylesheet" href="css/Login.css" type="text/css" />
<body>
	<header class="header">
    	<p class="system">タスク管理システム</p>
    	<p class="name">4167 SYSTEM</p>
    </header>
    <main class="main">
		<form action="LoginServlet" method="post">
			 <p class="fsize">LOGIN</p>
			 <input type="text" placeholder="Username" name="userId" />
			 <input type="password" placeholder="Password" name="password" />
			 <button type="submit">Login</button>
		</form>
	</main>
</body>
</html>