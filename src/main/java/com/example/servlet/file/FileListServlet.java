package com.example.servlet.file;

import com.example.servlet.file.discover.LocalDir;
import com.example.servlet.file.discover.LocalFile;
import com.example.servlet.file.discover.UserPathPoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

@WebServlet("/filelist")
public class FileListServlet extends BaseFileServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserPathPoint userPathPoint = resolveUserPath(request, "path");

            List<LocalDir> dirs = new ArrayList<>();
            List<LocalFile> files = new ArrayList<>();

            try {
                listDirectoryContents(userPathPoint, dirs, files);
            } catch (IOException e) {
                forwardError(
                        request,
                        response,
                        userPathPoint.getParentPath().toString(),
                        "Ошибка просмотра директории"
                );
                return;
            }

            dirs.sort(Comparator.comparing(LocalDir::getPath));
            files.sort(Comparator.comparing(LocalFile::getPath));

            forwardSuccess(request, response, dirs, files, userPathPoint);
        } catch (SecurityException e) {
            forwardError(request, response, "", "У вас нет доступа!");
        }
    }

    private void listDirectoryContents(UserPathPoint userPathPoint, List<LocalDir> dirs, List<LocalFile> files) throws IOException {
        try (Stream<Path> stream = Files.list(userPathPoint.getAbsolutePath())) {
            stream.forEach(path -> {
                try {
                    if (Files.isDirectory(path)) {
                        dirs.add(new LocalDir(path, userPathPoint.getRelativePath()));
                    } else {
                        files.add(new LocalFile(path, userPathPoint.getRelativePath()));
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    private void forwardError(HttpServletRequest request, HttpServletResponse response,
                              String previousPath, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("previousPath", previousPath);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("file/filelist-error.jsp").forward(request, response);
    }

    private void forwardSuccess(HttpServletRequest request, HttpServletResponse response,
                                List<LocalDir> dirs, List<LocalFile> files,
                                UserPathPoint userPathPoint)
            throws ServletException, IOException {
        request.setAttribute("dirs", dirs);
        request.setAttribute("files", files);
        request.setAttribute("userPathPoint", userPathPoint);
        request.setAttribute("currentDate", new Date());
        request.getRequestDispatcher("file/filelist.jsp").forward(request, response);
    }
}
