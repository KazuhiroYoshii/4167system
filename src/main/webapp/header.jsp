<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<link rel="stylesheet" href="css/Header.css" type="text/css" />
<body>
	<header class="header">
		<div class="system_name">
	    	<p class="system">タスク管理システム</p>
	    	<p class="name">4167 SYSTEM</p>
    	</div>
    	<div class="login_username">
	    	<img class="img" src="img/icon.png">
	    	<p class="username"><%=session.getAttribute("userName") %> さん</p>
	    </div>
    </header>
</body>
</html>