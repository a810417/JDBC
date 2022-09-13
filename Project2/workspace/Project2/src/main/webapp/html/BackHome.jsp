<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>>管理系統</title>
		<style>
			a {
				text-decoration: none;
				font-size: larger;
				color: black;
			}

			button {
				border-radius: 10px;
				border: 1px solid gray;
			}
		</style>
	</head>

	<body align="center">
		<div>
			<h2>會 員 資 料 處 理</h2>
		</div>

		<form action="http://localhost:8080/Project2/SearchMem" method="get">
			<input type="text" name="searchText" placeholder="Search...">
			<input type="submit" name="searchBtn" value="search">
		</form>
		<br />
		<button>
			<a href="http://localhost:8080/Project2/GetAllMem">查詢所有會員資料</a>
		</button>
		<br>
		<br>
		<button>
			<a href="http://localhost:8080/Project2/html/NewMember.html">新增會員</a>
		</button>

	</body>

	</html>