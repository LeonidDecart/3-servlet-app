package com.example.store;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextProvider implements ServletContextListener {
    private static ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { }

    public static String getRealPath(String path) {
        return context.getRealPath(path);
    }
}
