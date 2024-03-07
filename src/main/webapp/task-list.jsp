<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.List, java.util.ArrayList, model.entity.UserCategoryStatusTaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧表示</title>
</head>
<body>
	<%
	//リクエストスコープ内の一覧表示用リストを取得
	List<UserCategoryStatusTaskBean> taskList = new ArrayList<>();
	taskList = (List) request.getAttribute("taskList");
	%>
	<h1>タスク一覧表示</h1>
	
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
			%>
			<tr>
				<td><%=task.getTaskName()%></td>
				<td><%=task.getCategoryName() %></td>
				<%
				if(task.getLimitDate() == null){
				%>
					<td>未設定</td>
				<%
				}else{
				%>
					<td><%=task.getLimitDate() %></td>
				<%
				}
				%>
				<td><%=task.getUserName() %></td>
				<td><%=task.getStatusName() %></td>
				<td><%=task.getMemo() %></td>
				
				<!-- 変更、削除ボタンから各サーブレットにtaskIdの値を送信 -->
				<td><form action="TaskAlterFormServlet" method="post">
					<button type="submit" value="<%=task.getTaskId() %>" name = "taskId">変更</button>
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