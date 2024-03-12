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
    	<p class="font">タスク管理システム【4167system】</p>
    </header>
    <div class="main">
    	<main>
			<form action="LoginServlet" method="post">
				 <p class="fsize">ログイン画面</p>
				 <input type="text" placeholder="Username" name="userId" />
				 <input type="password" placeholder="Password" name="password" />
				 <button type="submit">ログイン</button>
			</form>
		</main>
	</div>
</body>
</html>