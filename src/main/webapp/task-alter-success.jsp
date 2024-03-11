<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集完了</title>
</head>
<body>
	<%@ include file="login-check.jsp" %>
	<% 
		UserCategoryStatusTaskBean alteredTask 
			= (UserCategoryStatusTaskBean)request.getAttribute("alteredTask");
	%>
	<h1>タスク編集完了</h1>
	<hr>
	<h3>タスク情報を以下の内容で編集しました。</h3>
	<table border=1>
		<tr>
			<th>タスク名</th>
			<td><%=alteredTask.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=alteredTask.getCategoryName() %></td>
		</tr>
		<tr>
			<th>期限</th>
			<td>
				<%
				if(alteredTask.getLimitDate() == null){
				%>
				未設定
				<%
				} else {
				%>
					<%=alteredTask.getLimitDate() %>
				<%
				}
				%>
			</td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=alteredTask.getUserName() %></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=alteredTask.getStatusName() %></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=alteredTask.getMemo()%></td>
		</tr>
	</table><br>
	<table>
		<tr>
			<td><form action="TaskListServlet" method="post">
				<input type="submit" value="一覧画面に戻る">
			</form></td>
			<td><form action="menu.jsp">
				<input type="submit" value="メニュー画面に戻る">
			</form></td>
		</tr>
	</table>
</body>
</html>