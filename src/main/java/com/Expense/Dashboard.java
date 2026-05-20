package com.Expense;

import java.io.IOException;
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

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("userid")!= null)
		{
			resp.sendRedirect("Login.jsp");
			return;
		}

		try {

			String url = "mysql:jdbc://localhost:3306/Expense_Tracker";
			String user = "root";
			String password = "Avb@#&05";

			Connection con = DriverManager.getConnection(url,user,password);

			String querry = "Select * form expense WHERE userid=?" ;
			PreparedStatement ps = con.prepareStatement(querry);
			int id =(Integer) session.getAttribute("userid");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			ArrayList<Expense> list = new ArrayList<>();
			while(rs.next()) {

				Expense e = new Expense(
						rs.getInt("id"),
						rs.getInt("userid"),
						rs.getString("title"),
						rs.getDouble("amount"),
						rs.getDate("date")
						);
				list.add(e);
			}

			req.setAttribute("expList", list);
			req.getRequestDispatcher("dash2.jsp").forward(req, resp);
 		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
