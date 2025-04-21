package com.example.servlet;

import com.example.accounts.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        AccountService accountService = (AccountService) getServletContext().getAttribute(AccountService.ACCOUNT_SERVICE_ATTRIBUTE);
        if (accountService == null) {
            accountService = new AccountService();
            getServletContext().setAttribute(AccountService.ACCOUNT_SERVICE_ATTRIBUTE, accountService);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        resp.sendRedirect("login");
    }
}