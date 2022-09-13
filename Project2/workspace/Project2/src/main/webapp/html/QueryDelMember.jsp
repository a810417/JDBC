<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>會員資料</title>
		<style>
			@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700;900&display=swap");

			*,
			body {
				font-family: "Poppins", sans-serif;
				font-weight: 400;
				-webkit-font-smoothing: antialiased;
				text-rendering: optimizeLegibility;
				-moz-osx-font-smoothing: grayscale;
			}

			html,
			body {
				height: 100%;
				background-color: #152733;
				overflow: hidden;
			}

			.form-holder {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				text-align: center;
				min-height: 100vh;
			}

			.form-holder .form-content {
				position: relative;
				text-align: center;
				display: -webkit-box;
				display: -moz-box;
				display: -ms-flexbox;
				display: -webkit-flex;
				display: flex;
				-webkit-justify-content: center;
				justify-content: center;
				-webkit-align-items: center;
				align-items: center;
				padding: 60px;
			}

			.form-content .form-items {
				border: 3px solid #fff;
				padding: 40px;
				display: inline-block;
				width: 100%;
				min-width: 540px;
				-webkit-border-radius: 10px;
				-moz-border-radius: 10px;
				border-radius: 10px;
				text-align: left;
				-webkit-transition: all 0.4s ease;
				transition: all 0.4s ease;
			}

			.form-content h3 {
				color: #fff;
				text-align: left;
				font-size: 28px;
				font-weight: 600;
				margin-bottom: 5px;
			}

			.form-content h3.form-title {
				margin-bottom: 30px;
			}

			.form-content p {
				color: #fff;
				text-align: left;
				font-size: 17px;
				font-weight: 300;
				line-height: 20px;
				margin-bottom: 30px;
			}

			.form-content label,
			.was-validated .form-check-input:invalid~.form-check-label,
			.was-validated .form-check-input:valid~.form-check-label {
				color: #fff;
			}

			.form-content input[type="text"],
			.form-content input[type="password"],
			.form-content input[type="email"],
			.form-content select {
				width: 90%;
				padding: 9px 20px;
				text-align: left;
				border: 0;
				outline: 0;
				border-radius: 6px;
				background-color: #fff;
				font-size: 15px;
				font-weight: 300;
				color: #8d8d8d;
				-webkit-transition: all 0.3s ease;
				transition: all 0.3s ease;
				margin-top: 16px;
			}

			.btn-primary {
				background-color: #6c757d;
				outline: none;
				border: 0px;
				box-shadow: none;
			}

			.btn-primary:hover,
			.btn-primary:focus,
			.btn-primary:active {
				background-color: #495056;
				outline: none !important;
				border: none !important;
				box-shadow: none;
			}

			.form-content textarea {
				position: static !important;
				width: 100%;
				padding: 8px 20px;
				border-radius: 6px;
				text-align: left;
				background-color: #fff;
				border: 0;
				font-size: 15px;
				font-weight: 300;
				color: #8d8d8d;
				outline: none;
				resize: none;
				height: 120px;
				-webkit-transition: none;
				transition: none;
				margin-bottom: 14px;
			}

			.form-content textarea:hover,
			.form-content textarea:focus {
				border: 0;
				background-color: #ebeff8;
				color: #8d8d8d;
			}

			.mv-up {
				margin-top: -9px !important;
				margin-bottom: 8px !important;
			}

			.invalid-feedback {
				color: #ff606e;
			}

			.valid-feedback {
				color: #2acc80;
			}

			td {
				color: #fff;
				text-align: left;
				font-size: 20px;
				font-weight: 450;
				margin-bottom: 5px;
			}

			a {
				text-decoration: none;
			}
		</style>
		<script>
			(function () {
				'use strict'
				const forms = document.querySelectorAll('.requires-validation')
				Array.from(forms)
					.forEach(function (form) {
						form.addEventListener('submit', function (event) {
							if (!form.checkValidity()) {
								event.preventDefault()
								event.stopPropagation()
							}

							form.classList.add('was-validated')
						}, false)
					})
			})()

		</script>
	</head>

	<body>
		<jsp:useBean id="mem" scope="request" class="com.tylu.bean.MemBean" />
		<div class="form-body">
			<div class="row">
				<div class="form-holder">
					<div class="form-content">
						<div class="form-items">
							<h3>刪 除 會 員 資 料</h3>
							<form method="get" action="http://localhost:8080/Project2/DeleteMem">

								<table>
									<tr>
										<td>會員ID</td>
										<td class="mb-3 mr-1"><input id="userID" name="userID" type="text" readonly
												value="<%=mem.getUserID()%>" class="form-control"></td>
									</tr>
									<tr>
										<td>會員帳號</td>
										<td class="mb-3 mr-1"><input id="userAccount" name="userAccount" type="text"
												readonly value="<%=mem.getUserAccount()%>" class="form-control"></td>
									</tr>
									<tr>
										<td>會員密碼</td>
										<td class="mb-3 mr-1"><input id="userPassword" name="userPassword" type="text"
												readonly value="<%=mem.getUserPassword()%>" class="form-control">
										</td>
									</tr>
									<tr>
										<td>性別</td>
										<td class="mb-3 mr-1"><select name="userGender" id="userGender" readonly>
												<option readonly selected>
													<%=mem.getUserGender()%>
												</option>
											</select></td>
									</tr>
									<tr>
										<td>身高</td>
										<td class="mb-3 mr-1"><input id="userHeight" name="userHeight" type="text"
												readonly value="<%=mem.getUserHeight()%>" class="form-control"></td>
									</tr>
									<tr>
										<td>體重</td>
										<td class="mb-3 mr-1"><input id="userWeight" name="userWeight" type="text"
												readonly value="<%=mem.getUserWeight()%>" class="form-control"></td>
									</tr>
								</table>
								<h3 style="color: #fff">確 定 刪 除 ?</h3>
								<a href="http://localhost:8080/Project2/GetAllMem"><button
										class="btn btn-primary">返回</button></a>

								<input type="submit" value="刪除" class="btn btn-primary" />

							</form>
						</div>
					</div>
				</div>
			</div>
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