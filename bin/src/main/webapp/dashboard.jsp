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
	<%
    		Integer userid = (Integer) session.getAttribute("userid");
    		if(userid == null){
        		response.sendRedirect("Login.jsp");
        		return;
    		}
	%>
	<%
		ArrayList<Expense> l1 = (ArrayList<Expense>) request.getAttribute("expenselist");
		if(l1 == null){
	    		out.println("No expenses found OR servlet not called.");
	    		return;
		}
	%>

	<table border = "1">
		<tr>
			<th>ID</th>
			<th>TITLE</th>
			<th>AMOUNT</th>
			<th>DATE</th>
			<th>ACTION</th>
		</tr>
	<%
		for(Expense e:l1){
	%>
		<tr>
			<td><%=e.getid()%></td>
			<td><%=e.getTitle()%></td>
			<td><%=e.getAmount() %></td>
			<td><%=e.getDate()%></td>
			<td>
				<a href ="delete?id=<%=e.getid()%>">Delete</a>
				<a href ="edit?id=<%=e.getid()%>">Edit</a>
			</td>
		</tr>
			
	<%
		}
	%>
	</table>
	
	<button onclick="window.location.href='dashboard.jsp'">Back</button>
</body>
</html>