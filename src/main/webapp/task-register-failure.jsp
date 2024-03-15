<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.UserCategoryStatusTaskBean"%>
<!-- タスク登録の失敗画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録失敗｜4167 SYSTEM</title>
</head>
<body>
	<%@ include file ="login-check.jsp" %>
	
	<%
	UserCategoryStatusTaskBean taskInfo 
		= (UserCategoryStatusTaskBean) request.getAttribute("taskInfo");
	%>
	<h3>タスクを登録できませんでした。</h3>
	<table border=1>
		<tr>
			<th>タスク名</th>
			<td><%=taskInfo.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td><%=taskInfo.getCategoryName()%></td>
		</tr>
		<tr>
			<th>期限</th>
			<%
				if(taskInfo.getLimitDate() == ""){
			%>
					<td>未設定</td>
			<%
				}else{
			%>
					<td><%=taskInfo.getLimitDate() %>　</td>
			<%
				}
			%>
		</tr>
		<tr>
			<th>担当者</th>
			<td><%=taskInfo.getUserName()%></td>
		</tr>
		<tr>
			<th>ステータス</th>
			<td><%=taskInfo.getStatusName()%></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=taskInfo.getMemo()%></td>
		</tr>
	</table>
	<br>
	<button class="btn" id="whiteBtn" onclick="history.back()" >タスク登録に戻る</button>
	<button class="btn" id="whiteBtn" onclick="location.href='menu.jsp'" >メニューに戻る</button>
</body>
</html>