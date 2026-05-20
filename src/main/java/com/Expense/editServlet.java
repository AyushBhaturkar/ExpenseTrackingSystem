package com.Expense;

import java.io.IOException;
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

@WebServlet("/edit")
public class editServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("userid") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String url = "jdbc:mysql://localhost:3306/Expense_Tracker";
        String user = "root";
        String pass = "Avb@#&05";
        String query = "Select * from expense where id = ? AND userid = ?";

        try {
            // Driver explicit load karna acchi practice hai
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Try-with-resources: con aur ps automatic securely close ho jayenge, chahe exception aaye ya na aaye
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Safe casting to avoid ClassCastException
            int userid = Integer.parseInt(session.getAttribute("userid").toString());

            ps.setInt(1, id);
            ps.setInt(2, userid);

            Expense e = null;

            // ResultSet ko bhi try ke andar rakha taaki yeh sabse pehle close ho
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    e = new Expense(
                        rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getString("title"),
                        rs.getDouble("amount"),
                        rs.getDate("date")
                    );
                }
            } // rs automatic yahan close ho gaya

            req.setAttribute("expense", e);

            // Forward hamesha sabse aakhri mein hota hai jab saara java logic poora ho chuka ho
            req.getRequestDispatcher("Editexpense.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            // Agar koi database error aayi toh blank page ki jagah screen par dikhegi
            resp.setContentType("text/html");
            resp.getWriter().println("<h3>Database/Servlet Error:</h3> <pre>" + e.getMessage() + "</pre>");
        }
    }
}