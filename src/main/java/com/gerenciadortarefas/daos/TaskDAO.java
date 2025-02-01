package com.gerenciadortarefas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gerenciadortarefas.database.DatabaseConnection;
import com.gerenciadortarefas.model.Task;

public class TaskDAO {

    private Connection connection;

    public TaskDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void create(Task task, int userId) throws SQLException {
        String query = "INSERT INTO tarefas (user_id, name, description, status, executed_at, finished_at) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, null);
            statement.setString(5, task.getExecutedAt().toString());
            statement.setString(6, task.getFinishedAt().toString());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void get() {

    }

    public List<Task> listByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tarefas ";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("executed_at") != null ? rs.getDate("executed_at").toLocalDate() : null,
                        rs.getDate("finished_at") != null ? rs.getDate("finished_at").toLocalDate() : null,
                        rs.getBoolean("completed")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public List<Task> listAll() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tarefas ";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("executed_at") != null ? rs.getDate("executed_at").toLocalDate() : null,
                        rs.getDate("finished_at") != null ? rs.getDate("finished_at").toLocalDate() : null,
                        rs.getBoolean("completed")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
    
}
