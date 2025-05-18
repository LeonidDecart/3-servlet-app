package com.example.servlet.auth;

import com.example.model.User;
import com.example.store.UserStore;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("auth/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String hash = Integer.toHexString(password.hashCode());

        User user = UserStore.getInstance().getUser(login);
        if (user == null || !user.getPasswordHash().equals(hash)) {
            req.setAttribute("error", "Неверный логин или пароль");
            doGet(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(60*60*24*30);
        resp.sendRedirect("filelist");
    }
}

