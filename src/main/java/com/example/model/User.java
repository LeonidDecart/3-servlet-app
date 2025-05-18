package com.example.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String passwordHash;
    private String email;

    public User() { }

    public User(String login, String passwordHash, String email) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }
}
