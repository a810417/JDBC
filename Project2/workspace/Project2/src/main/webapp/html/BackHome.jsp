<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Manage System</title>
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
		</style>
	</head>

	<body align="center">
		<div class="form-body">
			<div class="row">
				<div class="form-holder">
					<div class="form-content">
						<div class="form-items">

							<h3>Member Data</h3>


							<form action="http://localhost:8080/Project2/SearchMem" method="get">
								<input type="text" name="searchText" placeholder="Search...">
								<br />
								<br />
								<input type="submit" name="searchBtn" value="search" class="btn btn-primary"
									align="center">
							</form>

							<br />
							<a href="http://localhost:8080/Project2/GetAllMem" align="center"><button
									class="btn btn-primary">Search All Members
								</button></a>
							<br>
							<br>
							<a href="http://localhost:8080/Project2/html/NewMember.html" align="center"><button
									class="btn btn-primary">New Member</button></a>

							<br>
							<br>
							<a href="http://localhost:8080/Project2/html/HomePage.htmll" align="center"><button
									class="btn btn-primary">Home</button></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

	</html>