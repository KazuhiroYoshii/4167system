<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.UserCategoryStatusTaskBean"%>
<!-- タスク登録の完了画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録失敗画面</title>
</head>
<body>
	<%
	UserCategoryStatusTaskBean taskInfo 
		= (UserCategoryStatusTaskBean) session.getAttribute("taskInfo");
	%>
	<h1>タスク登録失敗画面</h1>
	<hr>
	<h3>タスク情報の登録に失敗しました。</h3>
	<table border=1>
		<tr>
			<th>タスク名</th>
			<td><%=taskInfo.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=taskInfo.getCategoryName()%></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=taskInfo.getLimitDate()%></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=taskInfo.getUserName()%></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=taskInfo.getStatusName()%></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=taskInfo.getMemo()%></td>
		</tr>
	</table>
	<br>

	<form action="TaskAddServlet" method="GET">
		<input type="submit" value="タスク登録画面に戻る">
	</form>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>

</body>
</html>