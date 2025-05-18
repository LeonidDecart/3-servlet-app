package com.example.servlet.file.discover;

import java.nio.file.Path;

public class UserPathPoint {
    private final Path homePath;
    private final Path absolutePath;
    private final Path relativePath;
    private final Path parentPath;

    public UserPathPoint(Path homePath, Path absolutePath, Path relativePath, Path parentPath) {
        this.homePath = homePath;
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
        this.parentPath = parentPath;
    }

    public Path getHomePath() {
        return homePath;
    }

    public Path getAbsolutePath() {
        return absolutePath;
    }

    public Path getRelativePath() {
        return relativePath;
    }

    public Path getParentPath() {
        return parentPath;
    }
}
