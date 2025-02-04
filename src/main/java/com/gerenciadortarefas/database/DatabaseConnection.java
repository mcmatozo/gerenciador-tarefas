package com.gerenciadortarefas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Define as credenciais de acesso ao banco de dados
    private static final String URL = "jdbc:mysql://wagnerweinert.com.br:3306/info22_mariaclara"; // URL do banco de dados MySQL
    private static final String USER = "info22_mariaclara"; // Nome de usuário do banco
    private static final String PASSWORD = "info22_mariaclara"; // Senha do banco

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        // Utiliza o DriverManager para estabelecer a conexão com o banco usando as credenciais fornecidas
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
