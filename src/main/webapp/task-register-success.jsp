<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.UserCategoryStatusTaskBean"%>
<!-- タスク登録の完了画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録成功｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/Result.css" type="text/css" />
<%@ include file ="header.jsp" %>
</head>
<body>
<main class="main">
	<%@ include file="login-check.jsp"%>

	<%
	UserCategoryStatusTaskBean taskInfo = (UserCategoryStatusTaskBean) request.getAttribute("taskInfo");
	%>
	<h3>タスク情報を登録しました。</h3>

	<table class="table" border=1>
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

	<button class="btn" id="whiteBtn" onclick="location.href='TaskListServlet'" >一覧画面に戻る</button>
	<button class="btn" id="whiteBtn" onclick="location.href='menu.jsp'" >メニュー画面に戻る</button>
</main>
</body>
</html>