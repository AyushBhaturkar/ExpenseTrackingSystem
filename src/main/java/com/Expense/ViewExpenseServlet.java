package com.Expense;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewExpense")
public class ViewExpenseServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("userid") == null) {
			resp.sendRedirect("Login.jsp");
			return;
		}


		PrintWriter out = resp.getWriter();
		try {
			String url ="jdbc:mysql://localhost:3306/Expense_Tracker";
			String user ="root";
			String passward ="Avb@#&05";

			Connection con = DriverManager.getConnection(url,user,passward);
			String query = "Select * from expense Where userid = ?";
			PreparedStatement ps = con.prepareStatement(query);
			int userid = (int) session.getAttribute("userid");
			ps.setInt(1, userid);

			ResultSet rs = ps.executeQuery();
			ArrayList<Expense> l1 = new ArrayList<>();
			while(rs.next())
			{
				Expense e = new Expense(
						rs.getInt("id"),
						rs.getInt("userid"),
						rs.getString("title"),
						rs.getDouble("amount"),
						rs.getDate("date")
						);
				l1.add(e);
			}
			req.setAttribute("expenselist", l1);
			req.getRequestDispatcher("ViewExpense.jsp").forward(req,resp);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
