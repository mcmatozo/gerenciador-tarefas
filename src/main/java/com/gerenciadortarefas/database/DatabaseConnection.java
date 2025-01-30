package com.gerenciadortarefas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://wagnerweinert.com.br:3306/info22_mariaclara";
    private static final String USER = "info22_mariaclara";
    private static final String PASSWORD = "info22_mariaclara";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}