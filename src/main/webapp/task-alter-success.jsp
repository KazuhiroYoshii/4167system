<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean"%>
<!-- タスク編集の完了画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集完了｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/Result.css" type="text/css" />
<!-- ヘッダー読み込み -->
<%@ include file ="header.jsp" %>
</head>
<body>
<main class="main">
	<%@ include file="login-check.jsp" %>
	<% 
		UserCategoryStatusTaskBean alteredTask 
			= (UserCategoryStatusTaskBean)request.getAttribute("alteredTask");
	%>
	<h3>タスクを以下の内容で編集しました。</h3>
	<table class="table" border=1>
		<tr>
			<th>タスク名</th>
			<td><%=alteredTask.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ</th>
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
			<th>担当者</th>
			<td><%=alteredTask.getUserName() %></td>
		</tr>
		<tr>
			<th>ステータス</th>
			<td><%=alteredTask.getStatusName() %></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=alteredTask.getMemo()%></td>
		</tr>
	</table><br>
	<button class="btn" id="whiteBtn" onclick="location.href='TaskListServlet'" >一覧画面に戻る</button>
</main>
</body>
</html>