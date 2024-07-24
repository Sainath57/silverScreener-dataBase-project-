package com.example.demo1;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")
public class register extends HttpServlet {
    //   private static final long serialVersionID = 1;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt());
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String country = request.getParameter("country");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root1234");
            String sql = "INSERT INTO register VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hashPassword);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, address);
            ps.setString(7, city);
            ps.setString(8, state);
            ps.setString(9, gender);
            ps.setString(10, String.valueOf(dob));
            ps.setString(11, String.valueOf(phoneNumber));
            ps.setString(12, country);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Registered Successfully');");
                out.println("window.location='register.jsp';</script>");
                response.sendRedirect("index.jsp;");
            }
            else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Please enter valid Details/Already Exist');");
                out.println("window.location='register.jsp';</script>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}