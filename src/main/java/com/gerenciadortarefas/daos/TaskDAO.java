package com.gerenciadortarefas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gerenciadortarefas.database.DatabaseConnection;
import com.gerenciadortarefas.model.Task;

public class TaskDAO {

    private Connection connection;

    public TaskDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void create(Task task) throws SQLException {
        String query = "INSERT INTO tarefas (name, description, status, executed_at, finished_at) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, null);
            statement.setString(4, task.getExecutedAt().toString());
            statement.setString(5, task.getFinishedAt().toString());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void get() {

    }
    
}
