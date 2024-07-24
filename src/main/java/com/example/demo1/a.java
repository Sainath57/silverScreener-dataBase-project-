package com.example.demo1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/a")
public class a extends HttpServlet {

    public void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    }
}
