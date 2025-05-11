package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

// Код написан для запуска через java -jar servlet-app.jar
public class Main {
    public static void main(String[] args) throws LifecycleException, IOException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8123);
        tomcat.getConnector();

        Path tempDir = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempDir.toString());

        Path tempJar = tempDir.resolve("app.jar");
        try (InputStream is = Main.class.getProtectionDomain().getCodeSource().getLocation().openStream()) {
            Files.copy(is, tempJar, StandardCopyOption.REPLACE_EXISTING);
        }

        Context context = tomcat.addWebapp("", tempDir.toString());

        StandardRoot resources = new StandardRoot(context);
        resources.addJarResources(new org.apache.catalina.webresources.JarResourceSet(
                resources, "/WEB-INF/classes", tempJar.toString(), "/"));
        resources.addJarResources(new org.apache.catalina.webresources.JarResourceSet(
                resources, "/", tempJar.toString(), "/META-INF/resources"));

        context.setResources(resources);
        context.setParentClassLoader(Main.class.getClassLoader());

        tomcat.start();
        tomcat.getServer().await();
    }
}