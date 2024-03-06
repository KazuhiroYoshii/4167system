<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集完了</title>
</head>
<body>
	<% 
		UserCategoryStatusTaskBean task 
			= (UserCategoryStatusTaskBean)request.getAttribute("task");
	%>
	<h1>タスク編集完了</h1>
	<hr>
	<h3>タスク情報の編集に成功しました。</h3>
	<table border=1>
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=task.getCategoryName() %></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=task.getLimitDate() %></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=task.getUserName() %></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=task.getStatusName() %></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=task.getMemo() %></td>
		</tr>
	</table><br>
	<form action="TaskListServlet" method="post">
		<input type="submit" value="一覧画面へ">
	</form>
</body>
</html>