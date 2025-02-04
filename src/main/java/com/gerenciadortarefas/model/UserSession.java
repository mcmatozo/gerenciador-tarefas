package com.gerenciadortarefas.model;

public class UserSession {

    // A variável 'instance' é usada para implementar o padrão Singleton
    private static UserSession instance;

    // A variável 'loggedUser' vai armazenar o usuário que está atualmente logado
    private User loggedUser;

    // Construtor privado, impedindo a criação de instâncias externas
    private UserSession() {}

    // Método para acessar a instância única da classe UserSession (Padrão Singleton)
    public static UserSession getInstance() {
        // Se a instância ainda não foi criada, cria uma nova
        if (instance == null) {
            instance = new UserSession();
        }
        return instance; // Retorna a instância única
    }

    // Método para definir o usuário logado
    public void setUser(User user) {
        this.loggedUser = user; // Atribui o usuário logado
    }

    // Método para obter o usuário logado
    public User getUser() {
        return loggedUser; // Retorna o usuário logado
    }

    // Método para limpar a sessão, ou seja, deslogar o usuário
    public void clearSession() {
        loggedUser = null; // Limpa a referência ao usuário logado
    }
}
