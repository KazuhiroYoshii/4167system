<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.List, java.util.ArrayList, model.entity.UserCategoryStatusTaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧表示画面</title>
</head>
<link rel="stylesheet" href="css/TaskList.css" type="text/css" />
<%@ include file ="login-check.jsp" %>
<%@ include file ="header.jsp" %>
<body>
	<main>
		<%
		//リクエストスコープ内の一覧表示用リストを取得
		List<UserCategoryStatusTaskBean> taskList = new ArrayList<>();
		taskList = (List) request.getAttribute("taskList");
		
		//セッションスコープからログイン済みユーザーのID、名前を取得
		String loggedInUserId = (String)session.getAttribute("userId");
		String loggedInUserName = (String)session.getAttribute("userName");
		%>
		<h1>TASK LIST</h1>
		<table class="table">
			<tbody>
				<tr>
					<th class="task">タスク名</th>
					<th class="category">カテゴリ</th>
					<th class="limit">期限</th>
					<th class="user">担当者</th>
					<th class="status">ステータス</th>
					<th class="memo">メモ</th>
					<th class="buttun"></th>
					<th class="buttun"></th>
					<th class="buttun"></th>
				</tr>
				<%
				//リクエストスコープから取得した一覧表示用リストを切り分け、表示
				for(UserCategoryStatusTaskBean task : taskList){
					
					//タスクID
					int taskId = task.getTaskId();
					
					//タスクごとのユーザーID
					String userIdOnTask = task.getUserId();
				%>
				<tr>
					<td class="task"><%=task.getTaskName()%></td>
					<td class="category"><%=task.getCategoryName() %></td>
					<td class="limit"><%=task.getLimitDate() %></td>
					<td class="user"><%=task.getUserName() %></td>
					<td class="status"><%=task.getStatusName() %></td>
					<td class="memo"><%=task.getMemo() %></td>
	
					<!-- 編集ボタンからサーブレットにtaskIdの値を送信 -->
					<td class="buttun"><form action="TaskAlterFormServlet" method="post">
						<div class="tooltip4">
							<button class="alter" type="submit" value="<%=taskId %>" name="taskId"
								<%
								//ログイン中ユーザー以外のタスク編集ボタンを非活性化
								if(!loggedInUserId.equals(userIdOnTask)){
								%>
									disabled 
								<%
								}
								%>>編集
							</button>
							<%
							//非活性化された編集ボタンにツールチップを付与
							if(!loggedInUserId.equals(userIdOnTask)){
							%>
								<div class="description4"><%=task.getUserName() %>さんのみ編集できます。</div>
							<%
							}
							%>
						</div>
					</form></td>
	
					<!-- 編集ボタンからサーブレットにtaskIdの値を送信 -->
					<td class="buttun"><form action="TaskDeleteServlet" method="post">
							<button class="delete" type="submit" value="<%=taskId %>" name="taskId">削除</button>
					</form></td>
	
					<!-- コメントボタンからサーブレットにtaskIdの値を送信 -->
					<td class="buttun"><form action="CommentServlet" method="get">
							<button class="comment" type="submit" value="<%=taskId %>" name="taskId">コメント</button>
					</form></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<button class="menu" onclick="location.href='menu.jsp'">メニュー画面に戻る</button>
	</main>
</body>
</html>