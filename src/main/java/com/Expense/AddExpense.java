package com.Expense;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/addexpenseServlet")
public class AddExpense extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(false);

		if(session == null || session.getAttribute("userid") == null ) {
			resp.sendRedirect("Login.jsp");
			return;
		}

		String amount_str = req.getParameter("amount");

		if(amount_str == null || amount_str.isEmpty()) {
		    out.println("Please enter a valid amount");
		    return;
		}

		Double amount = Double.parseDouble(amount_str);
		String description = req.getParameter("description");
		String date_strString = req.getParameter("date");
		java.sql.Date date = java.sql.Date.valueOf(date_strString);

		try {
			String url = "jdbc:mysql://localhost:3306/Expense_Tracker";
			String user = "root";
			String password = "Avb@#&05";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password);

			String query = "INSERT INTO expense (userid,title,amount,date) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			int userid = (Integer) session.getAttribute("userid");

			ps.setInt(1, userid);
			ps.setString(2, description);
			ps.setDouble(3, amount);
			ps.setDate(4, date);


			int i = ps.executeUpdate();
			if(i > 0){
				resp.sendRedirect("dashboard.jsp");
			}else {
				out.println("Something Went Wrong");
			}
			ps.close();
			con.close();

 		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
