<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除完了｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/TaskDeleteConfirm.css" type="text/css" />
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
	
	//リクエストスコープからコメントの削除実行件数を取得
	int commentDeleteResult = (int)request.getAttribute("commentDeleteResult");
	%>
	<%
	//コメントを削除した場合のみメッセージを変更
	if(commentDeleteResult > 0){
	%>
		<h3>以下の内容のタスクと、<%=commentDeleteResult %>件のコメントを削除しました。</h3>
	<%
	}else{
	%>
		<h3>以下の内容のタスクを削除しました。</h3>
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
	<button class="btn" id="whiteBtn" onclick="location.href='TaskListServlet'" >一覧画面に戻る</button>
	<button class="btn" id="whiteBtn" onclick="location.href='menu.jsp'" >メニュー画面に戻る</button>
</main>
</body>
</html>