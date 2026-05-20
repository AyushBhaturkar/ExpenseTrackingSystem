<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="addexpense.css">
</head>
<body>
	<form action = "addexpenseServlet" method = "post">
		<h3> Add Expenses </h3>
		<br><br>
		
		<div>
			Amount:
				<input type ="number" name ="amount" placeholder ="Enter your Amount">
				<br><br>
				
			Description:
				<input type ="text" name ="description" placeholder ="Enter Description">
				<br><br>
				
			Date:
				<input type ="date" name ="date">
				<br><br>
				
			<button type = "submit"> Submit </button>
		</div>
	</form>
</body>
</html>