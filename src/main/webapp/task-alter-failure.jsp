<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- タスク編集の失敗画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集失敗｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/Result.css" type="text/css" />
<!-- ヘッダー読み込み -->
<%@ include file ="header.jsp" %>
</head>
<body>
<main class="main">
	<%@ include file="login-check.jsp" %>
	<h3>タスクを編集できませんでした。</h3><br>
	<button class="btn" id="whiteBtn" onclick="location.href='TaskListServlet'" >一覧に戻る</button>
</main>
</body>
</html>