package com.example.servlet.auth;

import com.example.model.User;
import com.example.store.UserStore;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("auth/register.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String hash = Integer.toHexString(password.hashCode());

        User user = new User(login, hash, email);

        if (!UserStore.getInstance().addUser(user)) {
            req.setAttribute("error", "Пользователь с таким логином уже существует");
            doGet(req, resp);
            return;
        }
        resp.sendRedirect("login");
    }
}
