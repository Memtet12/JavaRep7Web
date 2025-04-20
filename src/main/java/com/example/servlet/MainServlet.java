package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/explorer")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String path = req.getParameter("path");

        if (path == null || path.isEmpty())
        {
            path = System.getProperty("user.home");
        }

        File directory = new File(path);

        String parentDir = directory.getParent();
        File[] files = directory.listFiles();
        System.out.println("Текущий путь:" + path);
        System.out.printf("Родительская папка" + parentDir);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String data = dateFormat.format(new Date());

        req.setAttribute("currentDir", path);
        req.setAttribute("parentDir",parentDir);
        req.setAttribute("files", files);
        req.setAttribute("creationDate", data);
        req.getRequestDispatcher("mypage.jsp").forward(req,resp);
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        super.doPost(req,resp);
    }
}
