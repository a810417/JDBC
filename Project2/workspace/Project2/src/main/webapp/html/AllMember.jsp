<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, com.tylu.bean.MemBean" pageEncoding="UTF-8"%>
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
				<th>會員體重 <%
				List<MemBean> mems = (ArrayList<MemBean>) request.getAttribute("mems");
				for (MemBean mem : mems) {
				%>
			<tr>
				<td><%=mem.getUserID()%>
				<td><%=mem.getUserAccount()%>
				<td><%=mem.getUserGender()%>
				<td><%=mem.getUserHeight()%>
				<td><%=mem.getUserWeight()%> <% } %>
		</table>
		<h3>
			共<%=mems.size()%>筆會員資料
		</h3>
	</div>
</body>

</html>