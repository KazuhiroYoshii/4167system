<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- タスクを登録する画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>

	<h1>タスク登録画面</h1>
	<hr>
	<form action="task-register-failure.jsp" method="POST">
		<table border="1">
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name" size="100"
					maxlength="100" required></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
						<!-- ステータスマスタ参照  -->

						<option value="1">新商品A：開発プロジェクト</option>
						<option value="2">既存商品B：改良プロジェクト</option>

				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date" required></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><input type="text" name="user_id" required></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">

						<!-- ステータスマスタ参照  -->

						<option value="00">未着手</option>
						<option value="50">着手</option>
						<option value="99">完了</option>

				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><textarea id="memo" name="memo" rows="5" cols="33">
					
					</textarea></td>
			</tr>
		</table>
		<br> 
		<input type="submit" value="登録実行">
		<input type="reset" value="クリア">
	</form>
	<br>
	<br>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">

	</form>

</body>
</html>