<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import= "java.util.List, java.util.ArrayList, model.entity.UserCategoryStatusTaskBean, 
		model.entity.UserCommentBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/Comment.css" type="text/css" />
<script src="js/deleteComment.js"></script>
<!-- ログイン状態を判定 -->
<%@ include file ="login-check.jsp" %>
<!-- ヘッダー読み込み -->
<%@ include file ="header.jsp" %>
</head>
<body>
<main class="main">
	<% 
	//リクエストスコープからタスクの詳細情報を取得
	UserCategoryStatusTaskBean taskDetail = (UserCategoryStatusTaskBean)request.getAttribute("taskDetail");
	%>
	<!-- タスク詳細情報 -->
	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>カテゴリ</th>
			<th>期限</th>
			<th>担当者</th>
			<th>ステータス</th>
			<th>メモ</th>
		</tr>
		<tr>
			<td><%=taskDetail.getTaskName() %></td>
			<td><%=taskDetail.getCategoryName() %>　</td>
			<td><%=taskDetail.getLimitDate() %></td>
			<td><%=taskDetail.getUserName() %></td>
			<td><%=taskDetail.getStatusName() %></td>
			<td><%=taskDetail.getMemo() %>　</td>
		</tr>
 	</table>
	
	<!-- コメント一覧、削除機能 -->
	<%
	//リクエストスコープからコメント情報を取得
	List<UserCommentBean> commentList = new ArrayList<>();
	commentList = (List) request.getAttribute("commentList");
	
	//セッションスコープからログイン中のユーザーIDを取得
	String loggedInUserId = (String)session.getAttribute("userId");
	%>
	<h1>コメント</h1>
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
			String commentUserId = commentData.getUserId();
		%>
			<tr>
				<td><%=commentData.getUserName() %></td>
				<td><%=commentData.getComment() %>　</td>
				<td><%=commentData.getUpdateDatetime() %></td>
				<td><form action="CommentDeleteServlet" method="post">
					<div class="tooltip4">
						<button class="btn btn-primary" onClick = "return checkDelete()"
								type="submit" value="<%=commentId %>" name="commentId"
						<%
						//ログイン中のユーザーとコメントしたユーザーが異なる場合は削除ボタン非活性
						if(!loggedInUserId.equals(commentUserId)){
						%>
							disabled
						<%
						}
						%>
						>削除</button>
					<%
					//非活性化された削除ボタンにツールチップを付与
					if(!loggedInUserId.equals(commentUserId)){
					%>
						<div class="description4"><%=commentData.getUserName() %>さんのみ削除できます。</div>
					<%
					}
					%>
					</div>
				</form></td>
			</tr>
		<%
		}
		%>
	</table>
		<%
		if(commentId == 0){
		%>
			<b><br>登録されているコメントはありません。</b>
		<%
		}
		%>
	<!-- コメント投稿機能 -->
	<h2>投稿</h2>
	<form action="CommentServlet" method="post">
		<textarea name="comment" rows="6" cols="50" maxlength="100" required></textarea>
		<br>
		<input type="reset" value="クリア">
		<input type="submit" value="投稿">
	</form>
	<br>
	<form action="TaskListServlet" method="get">
		<button type="submit">一覧に戻る</button>
	</form>
</main>
</body>
</html>