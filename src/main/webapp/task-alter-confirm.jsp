<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="login-check.jsp" %>
	<% 
		//セッションスコープから入力されたタスク情報を取得
		UserCategoryStatusTaskBean newTask = (UserCategoryStatusTaskBean)session.getAttribute("newTask");
	%>
	<h1>タスク編集確認画面</h1>
	<hr>
	<h3>タスク情報を以下の内容で編集しますか？</h3>
	<table border=1>
		<tr>
			<th>タスク名</th>
			<td><%=newTask.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=newTask.getCategoryName() %></td>
		</tr>
		<tr>
			<th>期限</th>
			<td>
				<%
				if(newTask.getLimitDate() == ""){
				%>
				未設定
				<%
				} else {
				%>
					<%=newTask.getLimitDate() %>
				<%
				}
				%>
			</td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=newTask.getUserName() %></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=newTask.getStatusName() %></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=newTask.getMemo()%></td>
		</tr>
	</table><br>
	<table>
		<tr>
			<td><form action="AlterExecuteServlet" method="post">
				<input type="submit" value="編集する">
			</form></td>
			<td><form action="TaskListServlet" method="post">
				<input type="submit" value="一覧画面に戻る">
			</form></td>
		</tr>
	</table>

</body>
</html>