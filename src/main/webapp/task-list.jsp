<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.List, java.util.ArrayList, model.entity.UserCategoryStatusTaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧表示</title>
<link rel="stylesheet" href="css/TaskList.css" type="text/css" />
</head>
<body>
	<!-- ログイン状態を判定 -->
	<%@ include file ="login-check.jsp" %>

	<%
	//リクエストスコープ内の一覧表示用リストを取得
	List<UserCategoryStatusTaskBean> taskList = new ArrayList<>();
	taskList = (List) request.getAttribute("taskList");
	
	//セッションスコープからログイン済みユーザーのID、名前を取得
	String loggedInUserId = (String)session.getAttribute("userId");
	String loggedInUserName = (String)session.getAttribute("userName");
	%>
	<h1>タスク一覧表示</h1>
	<h3>ログイン中：<%=loggedInUserName %>さん</h3>
	<hr>
		<table border = "1">
			<tr>
				<th>タスク名</th>
				<th>カテゴリ情報</th>
				<th>期限</th>
				<th>担当者情報</th>
				<th>ステータス情報</th>
				<th>メモ</th>
				<th>　</th>
				<th>　</th>
			</tr>
			<%
			//リクエストスコープから取得した一覧表示用リストを切り分け、表示
			for(UserCategoryStatusTaskBean task : taskList){
				
				//タスクごとのユーザーID
				String userIdOnTask = task.getUserId();
			%>
			<tr>
				<td><%=task.getTaskName()%>　</td>
				<td><%=task.getCategoryName() %>　</td>
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
				%>
				<td><%=task.getUserName() %>　</td>
				<td><%=task.getStatusName() %>　</td>
				<td><%=task.getMemo() %>　</td>
				
				<!-- 編集、削除ボタンから各サーブレットにtaskIdの値を送信 -->
				<td><form action="TaskAlterFormServlet" method="post">
					<div class="tooltip4">
						<button type="submit" value="<%=task.getTaskId() %>" name = "taskId"
						<%
						//ログイン中ユーザー以外のタスク編集ボタンを非活性化
						if(!loggedInUserId.equals(userIdOnTask)){
						%>
							disabled
						<%
						}
						%>
						>編集</button>
					<%
					//非活性化された編集ボタンにツールチップを付与
					if(!loggedInUserId.equals(userIdOnTask)){
					%>
						<div class="description4"><%=task.getUserName() %>さんのみ権限があります。</div>
					<%
					}
					%>
					</div>
				</form></td>
				<td><form action="TaskDeleteServlet" method="post" >
					<button type="submit" value="<%=task.getTaskId() %>" name = "taskId">削除</button>
				</form></td>
			</tr>
			<%
			}
			%>
		</table>
		<br>
		<button onclick="location.href='menu.jsp'" >メニュー画面に戻る</button>

</body>
</html>