<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- タスク登録の完了画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録失敗画面</title>
</head>
<body>
	<h1>タスク登録失敗画面</h1>
	<hr>
	<h1>タスク情報の登録に失敗しました。</h1>

	<form action="task-register.jsp" method="POST">
		<input type="submit"  value="タスク登録画面に戻る">
	</form>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>

</body>
</html>