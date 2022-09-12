<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, com.tylu.bean.MemBean"
	pageEncoding="UTF-8" %>
	<%!@SuppressWarnings("unchecked")%>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>後台 - 會員資料</title>
		</head>

		<body>
			<div align="center">
				<h2>會 員 資 料</h2>

				<form action="http://localhost:8080/Project2/SearchMem" method="get">
					<input type="text" name="searchText" placeholder="Search...">
					<input type="submit" name="searchBtn" value="search">
				</form>
				<br>
				<br>
				<table border="1">
					<tr style="background-color: #a8fefa">
						<th>會員ID
						<th>會員帳號
						<th>會員性別
						<th>會員身高
						<th>會員體重
						<th>修改
						<th>刪除 <% List<MemBean> mems = (ArrayList<MemBean>) request.getAttribute("mems");
									for (MemBean mem : mems) {
									%>
					<tr>
						<form method="post" id="updateForm<%=mem.getUserID()%>" action="" enctype="multipart/form-data">
							<td id="userId<%=mem.getUserID()%>" name="userId">
								<%=mem.getUserID()%>
							<td id="userAccount<%=mem.getUserID()%>" name="userAccount">
								<%=mem.getUserAccount()%>
							<td id="userGender<%=mem.getUserID()%>" name="userGender">
								<%=mem.getUserGender()%>
							<td id="userHeight<%=mem.getUserID()%>" name="userHeight">
								<%=mem.getUserHeight()%>
							<td id="userWeight<%=mem.getUserID()%>" name="userWeight">
								<%=mem.getUserWeight()%>
							<td><input id="update<%=mem.getUserID()%>" type="submit" value="修改" onclick="updateBtn()">
							<td><input id="delete<%=mem.getUserID()%>" type="submit" value="刪除" onclick="deleteBtn()">
						</form>

						<% } %>
				</table>
				<h3>
					共<%=mems.size()%>筆會員資料
				</h3>
			</div>
			<script src="https://code.jquery.com/jquery-3.6.1.js"
				integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
			<script>
				function deleteBtn() {
					// $(this).closest($("form"))
					console.log($(this).closest($("form")));
				}
			</script>
		</body>

		</html>