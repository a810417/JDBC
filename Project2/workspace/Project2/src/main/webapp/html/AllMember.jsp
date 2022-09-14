<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, com.tylu.bean.MemBean"
	pageEncoding="UTF-8" %>
	<%!@SuppressWarnings("unchecked")%>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>BackStage - MemberData</title>
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
					overflow: scroll;
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
					color: white;
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
					border: 0;
					background-color: #ebeff8;
					color: #707070;
				}

				th {
					border: 0;
					background-color: #ebeff8;
					color: #707070;
				}

				table {
					width: 95%;
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

		<body align="center">
			<div class="form-body">
				<div class="row">
					<div class="form-holder">
						<div class="form-content">
							<div class="form-items" align="center">
								<h3 style="color: white">Member</h3>
								<form action="http://localhost:8080/Project2/SearchMem" method="get" align="center">
									<input type="text" name="searchText" placeholder="Search...">
									<br>
									<br>
									<input type="submit" name="searchBtn" value="search" class="btn btn-primary">
								</form>
								<br>
								<table border="1">
									<tr>
										<th class="col-md-12">會員ID
										<th class="col-md-12">會員帳號
										<th class="col-md-12">會員性別
										<th class="col-md-12">會員身高
										<th class="col-md-12">會員體重
											<% List<MemBean> mems = (ArrayList<MemBean>) request.getAttribute("mems");
													for (MemBean mem : mems) {
													%>
									<tr>
										<td name="userId" class="col-md-12" value="<%=mem.getUserID()%>">
											<%=mem.getUserID()%>
										<td name="userAccount" class="col-md-12" value="<%=mem.getUserAccount()%>">
											<%=mem.getUserAccount()%>
										<td name="userGender" class="col-md-12" value="<%=mem.getUserGender()%>">
											<%=mem.getUserGender()%>
										<td name="userHeight" class="col-md-12" value="<%=mem.getUserHeight()%>">
											<%=mem.getUserHeight()%>
										<td name="userWeight" class="col-md-12" value="<%=mem.getUserWeight()%>">
											<%=mem.getUserWeight()%>

												<% } %>
								</table>
								<h3>
									共<%=mems.size()%>筆會員資料
								</h3>
								<br />
								<a href="http://localhost:8080/Project2/html/BackHome.jsp"><button
										class="btn btn-primary" align="center">
										Back
									</button></a>
								<a href="http://localhost:8080/Project2/html/Delete.jsp"><button class="btn btn-primary"
										align="center">
										Delete
									</button></a>

							</div>
						</div>
					</div>
				</div>
			</div>
			<script src="https://code.jquery.com/jquery-3.6.1.js"
				integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
			<script>
				$("#updateBtn").on("click", function () {
					let userID = $(this).prev().prev().prev().prev().prev().text();
					console.log(userID);
				})
			</script>

		</body>

		</html>