package com.example.servlet.file.discover;

import com.example.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.stream.Stream;

public class LocalDir {
    private final String name;
    private final String path;
    private final long itemCount;
    private final long size;
    private final String date;

    public LocalDir(Path dir, Path parentPath) throws IOException {
        name = dir.getFileName().toString();
        path = parentPath.resolve(name).toString();

        itemCount = calculateDirElements(dir);
        size = calculateDirSize(dir);

        date = AppConfig.DATE_FORMAT
                .format(new Date(Files.getLastModifiedTime(dir).toMillis()));
    }

    private long calculateDirSize(Path dir) throws IOException {
        try (Stream<Path> stream = Files.walk(dir)) {
            return stream
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0L;
                        }
                    })
                    .sum();
        }
    }

    private long calculateDirElements(Path dir) throws IOException {
        try (Stream<Path> stream = Files.walk(dir)) {
            return stream.count();
        }
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getItemCount() {
        return itemCount;
    }

    public long getSize() {
        return size;
    }

    public String getDate() {
        return date;
    }
}