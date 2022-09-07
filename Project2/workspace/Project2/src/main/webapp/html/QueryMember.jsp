<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>會員資料</title>
	</head>

	<body align="center">
		<jsp:useBean id="mem" scope="request" class="com.tylu.bean.MemBean" />
		<div align="center">
			<h2>修 改 會 員 資 料</h2>
			<form method="post" action="http://localhost:8080/Project2/UpdateMem" enctype="multipart/form-data">

				<table>
					<tr>
						<td>會員ID</td>
						<td><input id="userID" name="userID" type="text" readonly value="<%=mem.getUserID()%>"></td>
					</tr>
					<tr>
						<td>會員帳號</td>
						<td><input id="userAccount" name="userAccount" type="text" readonly
								value="<%=mem.getUserAccount()%>"></td>
					</tr>
					<tr>
						<td>會員密碼</td>
						<td><input id="userPassword" name="userPassword" type="text" value="<%=mem.getUserPassword()%>">
						</td>
					</tr>
					<tr>
						<td>性別</td>
						<td><select name="userGender" id="userGender">
								<option disabled selected>
									<%=mem.getUserGender()%>
								</option>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
							</select></td>
					</tr>
					<tr>
						<td>身高</td>
						<td><input id="userHeight" name="userHeight" type="text" value="<%=mem.getUserHeight()%>"></td>
					</tr>
					<tr>
						<td>體重</td>
						<td><input id="userWeight" name="userWeight" type="text" value="<%=mem.getUserWeight()%>"></td>
					</tr>
				</table>
				<h3 style="color: red">請 輸 入 新 資 料 !</h3>
				<input type="submit" value="修改" />
			</form>
		</div>
		<script>
			var idTag = document.getElementById("userID");
			var idTagValue = idTag.value;
			console.log(idTag);
			console.log(idTagValue);
			if (idTagValue === null) {
				console.log("NONONO");
			}
		</script>
	</body>

	</html>