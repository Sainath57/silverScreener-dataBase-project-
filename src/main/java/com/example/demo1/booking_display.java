package com.example.demo1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/booking_display")
public class booking_display extends HttpServlet {

    public void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = validation.username ;
        out.println("<html>");
        out.println("<body>");
        out.println("<center><h3>My Bookings</h3>");
        try {
            Class.forName("com.jdbc.cj.mysql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root1234");
            String sql = "SELECT * FROM booking where username = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"username");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.println("</body>");
        out.println("</html>");
    }
}
