package com.example.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final List<String> openUrls = List.of(
            "/login", "/register", "/css/", "/js/"
    );

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getRequestURI()
                .substring(request.getContextPath().length());

        boolean allowed = openUrls.stream().anyMatch(path::startsWith);
        if (!allowed && request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        chain.doFilter(req, response);
    }
}

