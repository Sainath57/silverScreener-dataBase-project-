package com.example.demo1;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class validation extends HttpServlet {
    public static String username;
    //private static final long serialVersionUID = 1;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        boolean hp = BCrypt.checkpw(password,hashedPassword);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root1234");
            String sql = "SELECT * FROM register WHERE username = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            //ps.setString(2, hashedPassword);
            HttpSession session = request.getSession();
            ResultSet rs = ps.executeQuery();
            if (rs.next() && hp) {
                session.setAttribute("username", username);
                String sql1 = "select firstName from register where username = ?";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1, username);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    String firstName = rs1.getString("firstName");
                    session.setAttribute("firstName", firstName);
                }
                    response.sendRedirect("UserHome.jsp");
            }
            else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Please enter valid Details/Already Exist');");
                out.println("window.location='login.jsp';</script>");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}