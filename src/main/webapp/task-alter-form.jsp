<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.entity.UserCategoryStatusTaskBean, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/TaskAlterForm.css" type="text/css" />
<title>タスク編集｜4167 SYSTEM</title>
</head>
<!-- ヘッダーを追加 -->
<%@ include file="header.jsp"%>
<body>
	<%@ include file="login-check.jsp"%>
	<%
	//選択されたタスク情報をセッションスコープから取得
	UserCategoryStatusTaskBean task = (UserCategoryStatusTaskBean) session.getAttribute("task");

	//選択肢のデータをリクエストスコープから取得
	List<UserCategoryStatusTaskBean> categoryList = (List<UserCategoryStatusTaskBean>) request.getAttribute("categoryList");
	List<UserCategoryStatusTaskBean> userList = (List<UserCategoryStatusTaskBean>) request.getAttribute("userList");
	List<UserCategoryStatusTaskBean> statusList = (List<UserCategoryStatusTaskBean>) request.getAttribute("statusList");
	%>
	<div class="main">
		<h1>TASK EDIT</h1>
		<form action="TaskAlterServlet" method="post">
			<table border=1>
				<tr>
					<th>タスク名</th>
					<td><input type="text" name="task_name" size="100"
						maxlength="50" required value="<%=task.getTaskName()%>"> <input
						type="hidden" name="task_id" value="<%=task.getTaskId()%>">
					</td>
				</tr>
				<tr>
					<th>カテゴリ</th>
					<td><select name="category_id">
							<%
							for (UserCategoryStatusTaskBean category : categoryList) {
							%>
							<option value="<%=category.getCategoryId()%>"
								<%if (category.getCategoryId() == task.getCategoryId()) {%>
								selected <%}%>><%=category.getCategoryName()%></option>
							<%
							}
							%>
					</select></td>
				</tr>
				<tr>
					<th>期限</th>
					<td><input type="date" name="limit_date"
						value="<%=task.getLimitDate()%>" required></td>
				</tr>
				<tr>
					<th>担当者</th>
					<td><select name="user_id">
							<%
							for (UserCategoryStatusTaskBean user : userList) {
							%>
							<option value="<%=user.getUserId()%>"
								<%if (user.getUserId().equals(task.getUserId())) {%> selected
								<%}%>><%=user.getUserName()%></option>
							<%
							}
							%>
					</select></td>
				</tr>
				<tr>
					<th>ステータス</th>
					<td><select name="status_code">
							<%
							for (UserCategoryStatusTaskBean status : statusList) {
							%>
							<option value="<%=status.getStatusCode()%>"
								<%if (status.getStatusCode().equals(task.getStatusCode())) {%>
								selected <%}%>><%=status.getStatusName()%></option>
							<%
							}
							%>
					</select></td>
				</tr>
				<tr>
					<th>メモ</th>
					<td><textarea id="memo" name="memo" rows="4" cols="25"
							maxlength="100"><%=task.getMemo()%></textarea></td>
				</tr>
			</table>
			<table>
				<tr>
					<td class="menu"><button type="button" onclick="history.back()">
						メニューに戻る</button></td>
					<td class="btn"><button type="reset">取消</button>
						<button type="submit">編集する</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>