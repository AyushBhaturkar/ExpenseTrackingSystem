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
@WebServlet("/RegisterServlet")
public class Register extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();		// to print in web page

		String name = req.getParameter("name1");
		String email = req.getParameter("email1");
		String password = req.getParameter("pass1");

		// Creating Connection with Database
		try {

			String url = "jdbc:mysql://localhost:3306/Expense_Tracker";
			String user = "root";
			String dbpass ="Avb@#&05";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,dbpass);

			String querry = "INSERT INTO user(name,email,password) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(querry);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);

			int i = ps.executeUpdate();
			resp.setContentType("text/html");
			if(i > 0) {
				resp.sendRedirect("Login.jsp");
			}else {
				out.println("<h3 style='color:red'> Error Occured </h3>");
			}


			ps.close();
			con.close();

		}catch (Exception e) {
			e.printStackTrace();
			out.println("<h3 style='color:red'>Database Driver not found!</h3>");
		}


	}
}
