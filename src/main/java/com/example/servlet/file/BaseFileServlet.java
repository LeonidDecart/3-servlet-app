package com.example.servlet.file;

import com.example.config.AppConfig;
import com.example.model.User;
import com.example.servlet.file.discover.UserPathPoint;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public abstract class BaseFileServlet extends HttpServlet {
    protected UserPathPoint resolveUserPath(HttpServletRequest request, String paramName) throws SecurityException {
        User user = (User) request.getSession().getAttribute("user");
        Path homePath = AppConfig.USER_HOMES_DIR.resolve(user.getLogin());

        String relPath = Optional.ofNullable(request.getParameter(paramName))
                .orElse("");

        if (relPath.startsWith(File.separator)) {
            relPath = relPath.substring(File.separator.length());
        }

        Path absolutePath = homePath.resolve(relPath)
                .toAbsolutePath()
                .normalize();

        if (!absolutePath.startsWith(homePath)) {
            throw new SecurityException("Invalid path traversal attempt");
        }

        Path relativePath = Paths.get(
                File.separator + homePath.relativize(absolutePath));
        Path parentPath = Optional.ofNullable(relativePath.getParent()).orElse(relativePath);

        return new UserPathPoint(homePath, absolutePath, relativePath, parentPath);
    }
}