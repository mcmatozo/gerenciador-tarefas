package com.gerenciadortarefas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    
    public static void createTables() {
        String createTaskSql = "CREATE TABLE IF NOT EXISTS tarefas ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "user_id INT NOT NULL,"
                + "FOREIGN KEY (user_id) REFERENCES usuarios(id),"
                + "name VARCHAR(255) NOT NULL,"
                + "description TEXT NOT NULL,"
                + "status VARCHAR(255),"
                + "completed BOOLEAN DEFAULT false,"
                + "executed_at TIMESTAMP,"
                + "finished_at TIMESTAMP,"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                + ")";
        String createUserSql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255) NOT NULL,"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                + ")";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUserSql);
            stmt.execute(createTaskSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    //public static void insertSampleData() {
    //    String sql = "INSERT INTO tarefas (name, description, status, completed, executed_at, finished_at) VALUES "
    //            + "('Tarefa 1', 'Descrição da Tarefa 1', 'Pendente', false, NULL, NULL),"
    //            + "('Tarefa 2', 'Descrição da Tarefa 2', 'Em Progresso', false, NULL, NULL),"
    //            + "('Tarefa 3', 'Descrição da Tarefa 3', 'Concluída', true, '2023-01-01 10:00:00', '2023-01-01 12:00:00')";
    //    try (Connection conn = DatabaseConnection.getConnection();
    //         Statement stmt = conn.createStatement()) {
    //       stmt.execute(sql);
     //   } catch (SQLException e) {
     //       e.printStackTrace();
    //    }
    //}

    public static void main(String[] args) {
        createTables();
    }
}
