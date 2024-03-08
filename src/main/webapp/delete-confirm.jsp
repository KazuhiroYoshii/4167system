<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.UserCategoryStatusTaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
</head>
<body>
	<!-- ログイン状態を判定 -->
	<%@ include file ="login-check.jsp" %>

	<%
	//セッションスコープから選択したタスクの詳細情報を取得
	UserCategoryStatusTaskBean task = (UserCategoryStatusTaskBean)session.getAttribute("task");
	%>

	<h1>タスク削除確認画面</h1>
	<hr>
	<h3>こちらのタスクを削除してもよろしいですか？</h3>
	<table border = "1">
		<!-- テーブル内で詳細情報を切り出し、表示 -->
		<tr><th>タスク名</th><td><%=task.getTaskName() %></td></tr>
		<tr><th>カテゴリ情報</th><td><%=task.getCategoryName() %></td></tr>
		<tr><th>期限</th>
				<%
				//期限が登録されていない場合は「未設定」と表示
				if(task.getLimitDate() == null){
				%>
					<td>未設定</td>
				<%
				}else{
				%>
					<td><%=task.getLimitDate() %>　</td>
				<%
				}
				%></tr>
		<tr><th>担当者情報</th><td><%=task.getUserName() %></td></tr>
		<tr><th>ステータス情報</th><td><%=task.getStatusName() %></td></tr>
		<tr><th>メモ</th><td><%=task.getMemo() %></td></tr>
	</table>
	<br>
	<button onclick="location.href='DeleteExecuteServlet'" >削除する</button><br>
	<br>
	<button onclick="location.href='TaskListServlet'" >一覧画面に戻る</button>
</body>
</html>