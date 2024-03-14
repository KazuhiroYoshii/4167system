<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.UserCategoryStatusTaskBean"%>
<!-- タスク登録の完了画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録成功｜4167 SYSTEM</title>
</head>
<body>
	<%@ include file="login-check.jsp"%>

	<%
	UserCategoryStatusTaskBean taskInfo = (UserCategoryStatusTaskBean) request.getAttribute("taskInfo");
	%>
	<h1>タスク登録成功</h1>
	<hr>
	<h3>タスク情報を登録しました。</h3>

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
			<%
			if (taskInfo.getLimitDate() == "") {
			%>
			<td>未設定</td>
			<%
			} else {
			%>
			<td><%=taskInfo.getLimitDate()%></td>
			<%
			}
			%>
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

	<table>
		<tr>
			<td>
				<form action="menu.jsp" method="POST">
					<input type="submit" value="メニューに戻る">
				</form>
			</td>
			<td>
				<form action="TaskListServlet" method="POST">
					<input type="submit" value="タスク一覧へ">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>