<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>刪除會員</title>
</head>

<body>
	<form action="../QueryDelMem" method="get">
		<h1>輸入帳號</h1>
		<div>
			<label for="account" class="t1">帳號</label> <input type="text"
				id="userAccount" name="userAccount" placeholder="account" autofocus
				required> <span id="idspAccount"></span><br />
		</div>

		<br />
		<div class="sub">
			<input type="submit" value="送出"> <input type="reset"
				value="清除">
		</div>
	</form>
</body>

</html>