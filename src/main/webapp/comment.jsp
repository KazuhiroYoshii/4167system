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
	//リクエストスコープからタスクの詳細情報を取得
	UserCategoryStatusTaskBean taskDetail = (UserCategoryStatusTaskBean)request.getAttribute("taskDetail");
	%>
	<h1>コメント</h1>
	<hr>
	<!-- タスク詳細情報 -->
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
			<td><%=taskDetail.getTaskName() %></td>
			<td><%=taskDetail.getCategoryName() %></td>
			<td><%=taskDetail.getLimitDate() %></td>
			<td><%=taskDetail.getUserName() %>　</td>
			<td><%=taskDetail.getStatusName() %>　</td>
			<td><%=taskDetail.getMemo() %>　</td>
		</tr>
	</table>
	<%
	//リクエストスコープからコメント情報を取得
	List<UserCommentBean> commentList = new ArrayList<>();
	commentList = (List) request.getAttribute("commentList");
	%>
	<h2>コメント一覧</h2>
	<%
	//削除実行用サーブレットで削除されたコメントがあればメッセージを表示
	int deleteResult = 2;
	deleteResult = (int)request.getAttribute("deleteResult");
	if(deleteResult == 1){
	%>
		<b><%=deleteResult %>件のコメントを削除しました。</b>
	<%
	}else if(deleteResult == 0){
	%>
		<b>コメントを削除できませんでした。</b>
	<%
	}
	%>
	<table border="1">
		<tr>
			<th>投稿者</th>
			<th>コメント内容</th>
			<th>投稿日時</th>
			<th>　</th>
		</tr>
		<%
		//取得したリストからコメント情報を切り出し、表示
		int commentId = 0;
		for(UserCommentBean commentData : commentList){
			commentId = commentData.getCommentId();
		%>
			<tr>
				<td><%=commentData.getUserName() %></td>
				<td><%=commentData.getComment() %>　</td>
				<td><%=commentData.getUpdateDatetime() %></td>
				<td><form action="CommentDeleteServlet" method="post">
					<button type="submit" value="<%=commentId %>" name="commentId">削除</button>
				</form></td>
			</tr>
		<%
		}
		%>
	</table>
		<%
		if(commentId == 0){
		%>
			<b>登録されているコメントはありません。</b>
		<%
		}
		%>
	<!-- コメント投稿機能 -->
	<h2>投稿</h2>
	<form action="CommentServlet" method="post">
		<textarea name="comment" rows="8" cols="50" maxlength="100"> </textarea>
		<br>
		<input type="submit" value="投稿">
	</form>
	<br>
	<form action="TaskListServlet" method="get">
		<button type="submit">一覧画面に戻る</button>
	</form>
</body>
</html>