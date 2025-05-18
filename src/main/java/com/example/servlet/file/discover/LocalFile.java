package com.example.servlet.file.discover;

import com.example.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class LocalFile {
    private final String name;
    private final String path;
    private final long size;
    private final String date;

    public LocalFile(Path file, Path parentPath) throws IOException {
        name = file.getFileName().toString();
        path = parentPath.resolve(name).toString();
        size = Files.size(file);
        date = AppConfig.DATE_FORMAT
                .format(new Date(Files.getLastModifiedTime(file).toMillis()));
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public String getDate() {
        return date;
    }
}
