<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"   import="java.sql.*,java.util.*,java.awt.*,java.awt.image.*,
java.util.*"%>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>會員資料</title>
	</head>

	<body style="background-color: #fdf5e6" align="center">
		<div align="center">
			<h2>新 增 會 員 資 料</h2>
			<jsp:useBean id="mem" scope="request" class="com.tylu.bean.MemBean" />
			<table>
				<tr>
					<td>會員帳號</td>
					<td><input type="text" disabled value="<%=mem.getUserAccount()%>"></td>
				</tr>
				<tr>
					<td>性別</td>
					<td><input type="text" disabled value="<%=mem.getUserGender()%>"></td>
				</tr>
				<tr>
					<td>身高</td>
					<td><input type="text" disabled value="<%=mem.getUserHeight()%>"></td>
				</tr>
				<tr>
					<td>體重</td>
					<td><input type="text" disabled value="<%=mem.getUserWeight()%>"></td>
				</tr>
				<tr>
					<td>會員圖片</td>
					<td><img src="http://localhost:8080/Project2/image/<%=mem.getUserID()%>.jpg" alt="MemberPhoto"
							width="400px" height="300px">
					</td>
					<td>
										
					</td>
					<br />
					<td><span><%=mem.getUserID()%></span>
					</td>
				</tr>


			</table>
			<h3 style="color: red">新 增 完 成 !</h3>
		</div>
	</body>

	</html>