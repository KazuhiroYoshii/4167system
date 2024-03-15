<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集確認｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/TaskAlterComfirm.css" type="text/css" />
</head>
<!-- ヘッダーを追加 -->
<%@ include file="header.jsp"%>
<body>
	<%@ include file="login-check.jsp"%>
	<%
	//セッションスコープから入力されたタスク情報を取得
	UserCategoryStatusTaskBean newTask = (UserCategoryStatusTaskBean) session.getAttribute("newTask");
	%>
	<div class="main">
		<h3>タスク情報を以下の内容で編集しますか？</h3>
		<table class="table" border=1>
			<tr>
				<th>タスク名</th>
				<td><%=newTask.getTaskName()%></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><%=newTask.getCategoryName()%></td>
			</tr>
			<tr>
				<th>期限</th>
				<td>
					<%
					if (newTask.getLimitDate() == "") {
					%> 未設定 <%
					} else {
					%> <%=newTask.getLimitDate()%> <%
 }
 %>
				</td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><%=newTask.getUserName()%></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><%=newTask.getStatusName()%></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><%=newTask.getMemo()%></td>
			</tr>
		</table>
		<br>
		<table class="btn_table">
			<tr>
				<td class="back"><button type="button" onclick="history.back()">
						編集フォームに戻る</button></td>
						
				<td class="btn"><form action="TaskAlterExecuteServlet"
						method="post">
						<button type="submit">編集する</button>
					</form></td>
			</tr>
		</table>
	</div>
</body>
</html>