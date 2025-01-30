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

    //public void insertTask(Task task) {
        //String sql = "INSERT INTO tarefas (name, description, status, executed_at, finished_at) VALUES (?, ?, ?, ?, ?)";

        //try (Connection conn = DatabaseConnection.getConnection();
         //    PreparedStatement stmt = conn.prepareStatement(sql)) {

          //      stmt.setString(1, task.getName());
          //      stmt.setString(2, task.getDescription());
          //      stmt.setString(3, null);
           //     stmt.setString(4, task.getExecutedAt().toString());
           //     stmt.setString(5, task.getFinishedAt().toString());
          //      stmt.executeUpdate();

        //} catch (SQLException e) {
        //    e.printStackTrace();
        //}
    //}

    public void get() {

    }
    
}
