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

@WebServlet("/delete")
public class deleteservlet extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(false);
		PrintWriter out = resp.getWriter();

		if(session == null || session.getAttribute("userid") == null) {
			resp.sendRedirect("dashboard.jsp");
			return;
		}
		int id = Integer.parseInt(req.getParameter("id"));

		try {
			String url = "jdbc:mysql://localhost:3306/Expense_Tracker";
			String user ="root";
			String pass = "Avb@#&05";

			Connection con = DriverManager.getConnection(url,user,pass);

			String query ="DELETE FROM expense WHERE id = ? AND userid = ?";

			PreparedStatement ps = con.prepareStatement(query);
			int userid = (Integer)session.getAttribute("userid");

			ps.setInt(1,id);
			ps.setInt(2, userid);


			int rs = ps.executeUpdate();
			if(rs > 0) {
				resp.sendRedirect("ViewExpense");
			}
			else {
				out.println("<h3 style='color:red'>Something Went Wrong</h3>");
			}
			con.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
