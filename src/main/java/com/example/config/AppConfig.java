package com.example.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class AppConfig {
    public static final Path USER_HOMES_DIR = Paths.get(
            System.getProperty("user.home"), "Desktop", "user_homes"
    );
    public static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
}