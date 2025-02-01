package com.gerenciadortarefas.model;

public class User {
    private String name;
    private String password;
    private String email;
    private int id;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;        
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this(0, name, email, password);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
