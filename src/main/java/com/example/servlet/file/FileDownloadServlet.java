package com.example.servlet.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.example.servlet.file.discover.UserPathPoint;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownloadServlet extends BaseFileServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            UserPathPoint userPathPoint = resolveUserPath(request, "path");
            download(response, userPathPoint.getAbsolutePath().toFile());
        } catch (SecurityException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        }
    }

    private void download(HttpServletResponse response, File file) throws IOException {

        if (!file.exists() || !file.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}