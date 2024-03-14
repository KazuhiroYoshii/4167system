<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/TaskConfirm.css" type="text/css" />
<!-- ログイン状態を判定 -->
<%@ include file ="login-check.jsp" %>
<!-- ヘッダー読み込み -->
<%@ include file ="header.jsp" %>
</head>
<body>
<main class="main">
	<%
	//セッションスコープから選択したタスクの詳細情報を取得
	UserCategoryStatusTaskBean task = (UserCategoryStatusTaskBean)session.getAttribute("task");
	
	//詳細情報からタスクIDを切り出し
	int taskId = task.getTaskId();
	
	//セッションスコープからタスクに付随するコメント数を取得
	int numberOfComments = (int)session.getAttribute("numberOfComments");
	%>
	<%
	//タスクにコメントが1件でもついている場合はメッセージを変更
	if(numberOfComments > 0){
	%>
		<h3>こちらのタスクにはコメントが<%=numberOfComments %>件付いています。</h3>
		<h3>削除してもよろしいですか？</h3>
	<%
	}else{
	%>
		<h3>こちらのタスクを削除してもよろしいですか？</h3>
	<%
	}
	%>
	<table class="table" border = "1">
		<!-- テーブル内で詳細情報を切り出し、表示 -->
		<tr><th>タスク名</th><td><%=task.getTaskName() %></td></tr>
		<tr><th>カテゴリ情報</th><td><%=task.getCategoryName() %></td></tr>
		<tr><th>期限</th><td><%=task.getLimitDate() %>　</td></tr>
		<tr><th>担当者情報</th><td><%=task.getUserName() %></td></tr>
		<tr><th>ステータス情報</th><td><%=task.getStatusName() %></td></tr>
		<tr><th>メモ</th><td><%=task.getMemo() %></td></tr>
	</table>
	<br>
	<%
	//タスクにコメントが1件でもついている場合は「コメントを見る」ボタンを追加
	if(numberOfComments > 0){
	%>
		<form action="CommentServlet" method="get">
			<input type="hidden" value="<%=taskId %>" name="taskId">
			<button class="menu" type="submit">コメントを見る</button>
		</form>
		<br>
	<%
	}
	%>
	<button class="menu" id="bottomBtn" onclick="location.href='TaskDeleteExecuteServlet'" >削除する</button>
	<button class="menu" id="bottomBtn" onclick="location.href='TaskListServlet'" >一覧画面に戻る</button>
</main>
</body>
</html>