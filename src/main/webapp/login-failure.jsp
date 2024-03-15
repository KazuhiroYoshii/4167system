<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン失敗｜4167 SYSTEM</title>
</head>
<link rel="stylesheet" href="css/LoginFailure.css" type="text/css" />
<body>
	<header class="header">
    	<p class="system">タスク管理システム</p>
    	<p class="name">4167 SYSTEM</p>
    </header>
    <main class="main">
		<p>ログインに失敗しました。<br>入力されたログイン情報が間違っています。</p>
		<form action="login.jsp" method="post">
			<button class="login" type="submit">ログイン</button>
		</form>
	</main>
</body>
</html>