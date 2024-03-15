<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.UserCategoryStatusTaskBean"%>
<!-- タスクを登録する画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録｜4167 SYSTEM</title>
<link rel="stylesheet" href="css/TaskAdd.css" type="text/css" />
<script src="js/date.js"></script>
</head>
<!-- ログイン状態を判定 -->
<%@ include file="login-check.jsp"%>
<!-- ヘッダーを追加 -->
<%@ include file="header.jsp"%>
<body>
	<div class="main">
		<main>
			<%
			// リクエストスコープから入力されたカテゴリ情報、ユーザ情報、ステータス情報を取得
			List<UserCategoryStatusTaskBean> categoryList = (List<UserCategoryStatusTaskBean>) request.getAttribute("CategoryList");
			List<UserCategoryStatusTaskBean> userList = (List<UserCategoryStatusTaskBean>) request.getAttribute("UserList");
			List<UserCategoryStatusTaskBean> statusList = (List<UserCategoryStatusTaskBean>) request.getAttribute("StatusList");

			//セッションスコープからログイン済みユーザーのID、名前を取得
			String loggedInUserId = (String) session.getAttribute("userId");
			%>
			<form action="TaskAddServlet" method="POST">
				<h1>TASK REGISTRATION</h1>
				<table border="1">
					<tr>
						<th>タスク名</th>
						<td><input type="text" name="task_name" maxlength="50"
							required></td>
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
						<td><input type="date" name="limit_date" id="today" min=""
							required></td>
					</tr>
					<tr>
						<th>担当者情報</th>
						<td><select name="user_id" required>
								<%
								for (UserCategoryStatusTaskBean user : userList) {
								%>
								<option value="<%=user.getUserId()%>"
									<%if (user.getUserId().equals(loggedInUserId)) {%> selected
									<%}%>>
									<%=user.getUserName()%></option>
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
						<td><textarea name="memo" rows="4" cols="25" maxlength="100"></textarea></td>
					</tr>
				</table>

				<table>
					<tr>
						<td class="btn"><button type="submit">登録する</button></td>
					</tr>
				</table>
			</form>
			<form action="menu.jsp" method="POST">
				<table>
					<tr><td class="menu">
							<input type="submit" value="メニューに戻る">
					</td></tr>
				</table>
			</form>
		</main>
	</div>

</body>
</html>