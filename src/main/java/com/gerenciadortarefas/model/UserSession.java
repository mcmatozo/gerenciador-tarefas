package com.gerenciadortarefas.model;

public class UserSession {
    private static UserSession instance;
    private User loggedUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(User user) {
        this.loggedUser = user;
    }

    public User getUser() {
        return loggedUser;
    }

    public void clearSession() {
        loggedUser = null;
    }
}