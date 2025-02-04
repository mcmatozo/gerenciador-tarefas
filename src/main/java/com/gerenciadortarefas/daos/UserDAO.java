package com.gerenciadortarefas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gerenciadortarefas.database.DatabaseConnection;
import com.gerenciadortarefas.model.User;

public class UserDAO {

    private Connection connection;

    // Construtor que inicializa a conexão com o banco de dados
    public UserDAO() throws SQLException {
        // Obtém a conexão com o banco através da classe DatabaseConnection
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para criar um novo usuário no banco de dados
    public void create(User user) throws SQLException {
        // SQL para inserção de um novo usuário na tabela "usuarios"
        String query = "INSERT INTO usuarios (name, email, password) VALUES (?, ?, ?)";
        
        try {
            // Preparação da instrução SQL com placeholders para os parâmetros
            PreparedStatement statement = connection.prepareStatement(query);
            // Atribui os valores aos parâmetros da query
            statement.setString(1, user.getName()); // Nome do usuário
            statement.setString(2, user.getEmail()); // Email do usuário
            statement.setString(3, user.getPassword()); // Senha do usuário
            // Executa a query para inserir o novo usuário no banco
            statement.executeUpdate();
        } catch (Exception e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        } finally {
            // Garante que a conexão será fechada após a execução
            connection.close();
        }
    }

    // Método para buscar um usuário pelo seu email
    public User findByEmail(String email) {

        // SQL para buscar um usuário na tabela "usuarios" baseado no email
        String query = "SELECT * FROM usuarios WHERE email = ?";
        
        try {
            // Preparação da instrução SQL com placeholders para os parâmetros
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email); // Define o valor do parâmetro (email)
            // Executa a query e armazena o resultado em um ResultSet
            ResultSet rs = statement.executeQuery();

            // Avança para o primeiro (e único) resultado do ResultSet
            rs.next();
            // Cria um objeto User a partir dos dados retornados pelo banco
            User user = new User(
                rs.getInt("id"), // ID do usuário
                rs.getString("name"), // Nome do usuário
                rs.getString("email"), // Email do usuário
                rs.getString("password") // Senha do usuário
            );

            // Retorna o usuário encontrado
            return user;

        } catch (Exception e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
            // Lança uma exceção para indicar erro ao buscar o usuário
            throw new RuntimeException("Erro ao buscar usuário por email");
        }
    }
    
}
