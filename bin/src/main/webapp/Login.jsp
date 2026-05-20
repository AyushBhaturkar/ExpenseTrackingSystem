<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Login.css">
</head>

<body>
	<form action = "LoginServlet" method = "post">
	 	<div class="outer">
			<div class="inner">
				<h3> Login </h3>
	 			Email 
	 			<input type = "text" name = "email1" placeholder = "Enter your email id" required>
	 			<br><br>
	 		
	 			Password
	 			<input type = "password" name = "pass1" placeholder = "Enter your password" required>
	 			<br><br>
				<button type = "submit" class="button">Submit</button>
	 		</div>
		</div>
	</form>
</body>
</html>