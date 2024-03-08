<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.UserCategoryStatusTaskBean"%>
<!-- タスクを登録する画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<%
	List<UserCategoryStatusTaskBean> categoryList = (List<UserCategoryStatusTaskBean>) request.getAttribute("CategoryList");
	List<UserCategoryStatusTaskBean> userList = (List<UserCategoryStatusTaskBean>) request.getAttribute("UserList");
	List<UserCategoryStatusTaskBean> statusList = (List<UserCategoryStatusTaskBean>) request.getAttribute("StatusList");
	%>

	<h1>タスク登録画面</h1>
	<hr>
	<form action="TaskAddServlet" method="POST">
		<table border="1">
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name" size="50"
					maxlength="50" required></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id" required>
						<%
						for (UserCategoryStatusTaskBean category : categoryList) {
						%>
						<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id" required>
						<%
						for (UserCategoryStatusTaskBean user : userList) {
						%>
						<option value="<%=user.getUserId()%>"><%=user.getUserName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code" required>
						<%
						for (UserCategoryStatusTaskBean status : statusList) {
						%>
						<option value="<%=status.getStatusCode()%>"><%=status.getStatusName()%></option>
						<%
						}
						%>

				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><textarea name="memo" rows="4" cols="25" maxlength="100"></textarea>
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" value="登録実行"> 
		<input type="reset" value="クリア">
	</form>
	<br>
	<br>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">

	</form>

</body>
</html>