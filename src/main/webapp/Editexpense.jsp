<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Expense.Expense" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="editexpense.css">
</head>
<body>
	<%
    		Object userObj = session.getAttribute("userid");
    		if(userObj == null){
        		response.sendRedirect("Login.jsp");
        return;
    		}
    		String name = userObj.toString();
	%>
	<%
		Expense e1 = (Expense) request.getAttribute("expense");
		if(e1 == null){
			out.println("No expenses found OR servlet not called.");
    			return;
		}
	%>
	<form action ="update" method="post">
		ID :<input type = "hidden" name = "id" value ="<%= e1.getid()%>"><br><br>
		Title : <input type = "text" name = "title" value ="<%= e1.getTitle()%>"><br><br>
		Amount : <input type = "text" name = "amount" value ="<%= e1.getAmount()%>"><br><br>
		Date : <input type = "date" name = "date" value ="<%= e1.getDate()%>"><br><br>
		
		<button type= "submit">Submit</button>
	</form>
</body>
</html>