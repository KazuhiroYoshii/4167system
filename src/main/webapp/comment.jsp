<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import= "java.util.List, java.util.ArrayList, model.entity.UserCategoryStatusTaskBean, 
	model.entity.UserCommentBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント</title>
</head>
<body>
	<% 
		UserCategoryStatusTaskBean taskDetail = 
				(UserCategoryStatusTaskBean)session.getAttribute("taskDetail");
		List<UserCommentBean> commentList = new ArrayList<>();
		commentList = (List) request.getAttribute("commentList");
	%>

	<h1>コメント</h1>
	<hr>
	<h2>タスク情報</h2>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>カテゴリ情報</th>
			<th>期限</th>
			<th>担当者情報</th>
			<th>ステータス情報</th>
			<th>メモ</th>
		</tr>
		<tr>
			<th><%=taskDetail.getTaskName() %></th>
			<th><%=taskDetail.getCategoryName() %></th>
			<th><%=taskDetail.getLimitDate() %></th>
			<th><%=taskDetail.getCategoryName() %></th>
			<td><%=taskDetail.getUserName() %>　</td>
			<td><%=taskDetail.getStatusName() %>　</td>
			<td><%=taskDetail.getMemo() %>　</td>
		</tr>

	</table>
	<br>
	<h2>コメント一覧</h2>
	<table border="1">
		<tr>
			<th>投稿者</th>
			<th>コメント内容</th>
			<th>投稿日時</th>
		</tr>
	</table>

	<br>
	<form action="CommentServlet" method="post">
		<h2>投稿</h2>
		<textarea id="memo" name="memo" rows="8" cols="50" maxlength="100"> </textarea>
		<br>
		<input type="submit" value="投稿">
	</form>
</body>
</html>