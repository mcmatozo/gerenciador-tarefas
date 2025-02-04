package com.gerenciadortarefas.model;

public class User {

    // Atributos da classe User
    private String name;      // Nome do usuário
    private String password;  // Senha do usuário
    private String email;     // E-mail do usuário
    private int id;           // Identificador único do usuário (ID)

    // Construtor com todos os parâmetros (incluindo 'id')
    public User(int id, String name, String email, String password) {
        this.id = id;         // Atribui o ID do usuário
        this.name = name;     // Atribui o nome do usuário
        this.email = email;   // Atribui o e-mail do usuário
        this.password = password; // Atribui a senha do usuário
    }

    // Construtor sem o 'id' (usado para criar um novo usuário sem um ID definido)
    public User(String name, String email, String password) {
        this(0, name, email, password);  // Chama o construtor anterior com 'id' igual a 0
    }

    // Métodos de acesso (getters) para cada atributo da classe

    // Retorna o nome do usuário
    public String getName() {
        return name;
    }

    // Retorna a senha do usuário
    public String getPassword() {
        return password;
    }

    // Retorna o e-mail do usuário
    public String getEmail() {
        return email;
    }

    // Retorna o ID do usuário
    public int getId() {
        return id;
    }
}
