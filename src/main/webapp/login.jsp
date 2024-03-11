<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン画面</h1>
	<hr>
	<form action="LoginServlet" method="post">
		<table>
			<tr>
				<td align="center" valign="middle"><b>ユーザ名</b></td>
				<td><input type="text" name="userId"></td>
			</tr>
			
			<tr>
				<td align="center" valign="middle"><b>パスワード</b></td>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>