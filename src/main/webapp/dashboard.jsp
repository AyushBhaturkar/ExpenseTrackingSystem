<%@page import="java.util.ArrayList"%>
<%@page import="com.Expense.Expense"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel= "stylesheet" type="text/css" href ="dashboard.css">
</head>
<body>
	<%
		String name = (String) session.getAttribute("name");
		if(name == null){
			response.sendRedirect("Login.jsp");
		}
		
	%>
	<div class = "outer">
		<h2> WELCOME <%= name %></h2>
	  	<nav class="navbar">
		  	<a href = "addexpense.jsp">ADD EXPENSE</a>
		  	<a href = "ViewExpense">VIEW EXPENSE</a>
		  	<a href = "logoutServlet">LOGOUT</a>
	  	</nav>
	</div>
	
</body>
</html>