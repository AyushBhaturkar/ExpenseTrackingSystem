package com.Expense;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("userid") == null ) {
			resp.sendRedirect("Login.jsp");
			return;
		}

		int id =Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		Double amount = Double.parseDouble(req.getParameter("amount"));
		String dstr = req.getParameter("date");
		Date date = Date.valueOf(dstr);

		try {
			String url = "jdbc:mysql://localhost:3306/Expense_Tracker";
			String user = "root";
			String pass = "Avb@#&05";

			Connection con = DriverManager.getConnection(url,user,pass);
			String query = "UPDATE expense SET title = ? ,amount = ? , date = ? WHERE id = ? AND userid = ?";

			PreparedStatement ps = con.prepareStatement(query);
			int userid = (Integer) session.getAttribute("userid");
			ps.setString(1, title);
			ps.setDouble(2, amount);
			ps.setDate(3, date);
			ps.setInt(4, id);
			ps.setInt(5, userid);

			int i = ps.executeUpdate();
			if(i > 0) {
				resp.sendRedirect("ViewExpense");
			}else {
				out.println("<h3 style ='color: red'>Something Went wrong</h3>");
			}
			con.close();
			ps.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
