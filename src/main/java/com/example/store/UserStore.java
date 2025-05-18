package com.example.store;

import com.example.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private static final String STORE_PATH = "/WEB-INF/users.ser";

    private static UserStore instance;
    private Map<String, User> users;

    private UserStore() {
        users = loadFromDisk();
    }

    public static synchronized UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    private Map<String, User> loadFromDisk() {
        File file = new File(
                getServletContextRealPath() + STORE_PATH
        );
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveToDisk() {
        File file = new File(
                getServletContextRealPath() + STORE_PATH
        );
        file.getParentFile().mkdirs();
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        if (users.containsKey(user.getLogin())) return false;
        users.put(user.getLogin(), user);
        saveToDisk();
        return true;
    }

    public User getUser(String login) {
        return users.get(login);
    }

    private String getServletContextRealPath() {
        return ContextProvider.getRealPath("");
    }
}
