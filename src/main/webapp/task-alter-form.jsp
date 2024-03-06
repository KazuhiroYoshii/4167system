<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="model.entity.UserCategoryStatusTaskBean, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集画面</title>
</head>
<body>
	<% 
		UserCategoryStatusTaskBean task 
			= (UserCategoryStatusTaskBean)request.getAttribute("task");
		List<UserCategoryStatusTaskBean> categoryList 
			= (List<UserCategoryStatusTaskBean>) request.getAttribute("categoryList");
		List<UserCategoryStatusTaskBean> userList 
			= (List<UserCategoryStatusTaskBean>) request.getAttribute("userList");
		List<UserCategoryStatusTaskBean> statusList 
			= (List<UserCategoryStatusTaskBean>) request.getAttribute("statusList");
	%>
	<h1>タスク編集画面</h1>
	<hr>
	<form action="task-confilm-servlet" method="post">
		<table border=1>
			<tr>
				<th>タスク名</th>
				<td>
					<input type="text" size=50 name="task_name" value="<%=task.getTaskName()%>">
				</td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
						<%
						for(UserCategoryStatusTaskBean category : categoryList){
						%>
						<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td>
					<input type="date" name="limit_date" value="<%=task.getLimitDate() %>">
				</td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id">
						<%
						for(UserCategoryStatusTaskBean user : userList){
						%>
						<option value="<%=user.getUserId()%>"><%=user.getUserName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">
						<%
						for(UserCategoryStatusTaskBean status : statusList){
						%>
						<option value="<%=status.getStatusCode()%>"><%=status.getStatusName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td>
					<input type="text" size=50 name="memo" value="<%=task.getMemo() %>">
				</td>
			</tr>
		</table><br>
		<input type="submit" value="編集する">
		<input type="reset" value="取消">
	</form><br>
	<form action="TaskListServlet" method="post">
		<input type="submit" value="一覧画面へ">
	</form>
</body>
</html>