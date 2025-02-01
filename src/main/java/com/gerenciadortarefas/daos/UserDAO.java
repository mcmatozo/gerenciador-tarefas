package com.gerenciadortarefas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gerenciadortarefas.database.DatabaseConnection;
import com.gerenciadortarefas.model.User;

public class UserDAO {

    private Connection connection;

    public UserDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void create(User user) throws SQLException {
        String query = "INSERT INTO usuarios (name, email, password) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public User findByEmail(String email) {

        String query = "SELECT * FROM usuarios WHERE email = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            rs.next();
            User user = new User(
                rs.getInt("id"),
                rs.getString("name"), 
                rs.getString("email"), 
                rs.getString("password"));

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuário por email");
        }
    }
    
}
