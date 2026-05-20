<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration page </title>
<link rel="stylesheet" type="text/css" href="Register.css">
</head>
<body>
	<form action = "RegisterServlet" method = "post">
	 	<div>
	 		<h3> REGISTER </h3>
	 		Name : 
	 			<input type = "text" name = "name1" placeholder = "Enter your name" required>
	 			<br><br>
	 		Email: 
	 			<input type = "text" name = "email1" placeholder = "Enter your email id" required>
	 			<br><br>
	 		Password : 
	 			<input type = "text" name = "pass1" placeholder = "Enter your password" required>
	 			<br><br>
	 			
	 		<button type = "submit">Submit</button>
	 	</div>
	</form>
</body>
</html>