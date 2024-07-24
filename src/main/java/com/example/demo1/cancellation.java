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

@WebServlet("/cancellation")
public class cancellation extends HttpServlet {


    public static String cancellation_id;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String cancellation_id = Random_id.getId();
        float charges = Float.parseFloat(request.getParameter("totalPrice"));
        String cstatus = "";
        String cdate = request.getParameter("currentDate");
        String ctime = request.getParameter("currentTime");
        String reason = request.getParameter("reason");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root1234");
            String sql = "Insert into cancellation values(?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cancellation_id);
            ps.setString(2, String.valueOf(charges));
            ps.setString(3,cstatus);
            ps.setString(4,cdate);
            ps.setString(5,ctime);
            ps.setString(6,reason);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
