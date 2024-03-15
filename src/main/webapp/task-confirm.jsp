<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集｜4167 SYSTEM</title>
</head>
<body>
	<form action="task-confilm-servlet" method="post">
		<table>
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name" value="">
				</td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
						<%
						{
						%>
						<option value=""></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date" value="">
				</td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id">
						<%
						{ 
						%>
						<option value=""></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">
						<%
					{ 
					%>
						<option value=""></option>
						<%
					}
					%>
				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="textarea" name="memo" value="">
				</td>
			</tr>
			<tr>
				<input type="submit" value="編集する">
				<input type="reset" value="取消">
			</tr>
		</table>
	</form>
	<form action="task_list.jsp">
		<input type="submit" value="一覧画面へ">
	</form>
</body>
</html>