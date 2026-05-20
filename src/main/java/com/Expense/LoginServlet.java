package com.Expense;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();		// to print in web page

		String email = req.getParameter("email1").trim();
		String password = req.getParameter("pass1").trim();

		// Creating Connection with Database
		try {

			String url = "jdbc:mysql://localhost:3306/Expense_Tracker";
			String user = "root";
			String dbpass ="Avb@#&05";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,dbpass);

			String querry = "Select * from user Where email = ? And password =?";
			PreparedStatement ps = con.prepareStatement(querry);
			ps.setString(1, email);
			ps.setString(2,password)	;

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				HttpSession session = req.getSession();
				session.setAttribute("name",name);
				session.setAttribute("userid", id);


				System.out.println("Login Servlet hit");
				rs.close();
				ps.close();
				con.close();
				resp.sendRedirect("dashboard.jsp");
			}else {
				resp.sendRedirect("Register.jsp");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
