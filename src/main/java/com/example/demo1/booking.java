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

//@WebServlet({"/booking","/bookingsuccess", "/popup","/UserHome","/booking"})


@WebServlet({"/booking"})
public class booking extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String booking_id = Random_id.getId();
        //String seat_no = request.getParameter();
        String movie_id = request.getParameter("id");
        String bstatus = "Pending";
        String username = request.getParameter("username");
        int seat_no = Integer.parseInt(request.getParameter("selectedSeatsCount"));
        String showtime = request.getParameter("currentTime");
        String showdate = request.getParameter("currentDate");
        String blocation = request.getParameter("theatreID");
        String transaction_id = request.getParameter(payment.transaction_id);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root1234");
            String sql = "Insert into booking values(?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,booking_id);
            ps.setString(2, String.valueOf(seat_no));
            ps.setString(3,showtime);
            ps.setString(4,showdate);
            ps.setString(5,bstatus);
            ps.setString(6,blocation);
            ps.setString(7,username);
            ps.setString(8,movie_id);
            ps.setString(9,transaction_id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
