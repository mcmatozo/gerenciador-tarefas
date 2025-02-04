package com.gerenciadortarefas.repositories;

import java.sql.SQLException;

import com.gerenciadortarefas.daos.UserDAO;
import com.gerenciadortarefas.model.User;

public class UserRepository {

    // Instância de UserDAO, responsável por interagir com o banco de dados para operações relacionadas ao usuário
    private UserDAO userDAO;

    // Construtor que inicializa o UserDAO
    public UserRepository() {
        try {
            // Tenta criar uma instância de UserDAO para acessar o banco de dados
            this.userDAO = new UserDAO();
        } catch (SQLException e) {
            // Caso ocorra um erro ao tentar conectar ao banco, ele será impresso no console
            e.printStackTrace();
        }
    }

    // Método para salvar um novo usuário no banco de dados
    public void save(User user) {
        try {
            // Chama o método 'create' do UserDAO para inserir o usuário no banco
            userDAO.create(user);
        } catch (SQLException e) {
            // Se ocorrer um erro na inserção, a exceção é capturada e impressa no console
            e.printStackTrace();
        }
    }

    // Método para buscar um usuário no banco de dados pelo email
    public User findByEmail(String email) {
        // Chama o método 'findByEmail' do UserDAO e retorna o usuário encontrado
        return userDAO.findByEmail(email);
    }
}
