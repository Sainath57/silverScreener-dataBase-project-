package com.example.demo1;

import jakarta.servlet.http.HttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/payment")
public class payment extends HttpServlet {


    public static String transaction_id;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String transaction_id = Random_id.getId();
        float amount = Float.parseFloat(request.getParameter("totalPrice"));
        String pstatus = "Confirmed";
        String pdate = request.getParameter("currentDate");
        String ptime = request.getParameter("currentTime");
        String cancellation_id = request.getParameter(cancellation.cancellation_id);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root1234");
            String sql = "Insert into payment values(?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,transaction_id);
            ps.setString(2, String.valueOf(amount));
            ps.setString(3,pstatus);
            ps.setString(4,pdate);
            ps.setString(5,ptime);
            ps.setString(6,cancellation_id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
