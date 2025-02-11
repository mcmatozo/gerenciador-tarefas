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
import com.gerenciadortarefas.model.Locale;
import com.gerenciadortarefas.model.Task;

public class TaskDAO {

    private Connection connection;

    // Construtor que inicializa a conexão com o banco de dados
    public TaskDAO() throws SQLException {
        // Obtém a conexão com o banco através da classe DatabaseConnection
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para criar uma nova tarefa no banco de dados
    public void create(Task task, int userId) throws SQLException {
        // SQL para inserção de uma nova tarefa na tabela "tarefas"
        String query = "INSERT INTO tarefas (user_id, name, description, status, executed_at, finished_at, locale_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            // Preparação da instrução SQL com placeholders para os parâmetros
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, task.getName());
            
            if (task.getDescription() != null) statement.setString(3, task.getDescription()); else statement.setString(3, null);
            
            statement.setString(4, null);

            if (task.getExecutedAt() != null) statement.setString(5, task.getExecutedAt().toString()); else statement.setString(5, null);
            if (task.getFinishedAt() != null) statement.setString(6, task.getFinishedAt().toString()); else statement.setString(6, null);
            if (task.getLocale() != null) statement.setInt(7, task.getLocale().getId()); else statement.setNull(7, java.sql.Types.INTEGER);

            statement.executeUpdate();
        } catch (Exception e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }
    }

    public void update(Task task, int userId) throws SQLException {
        String query = "UPDATE tarefas SET name = ?, description = ?, executed_at = ?, finished_at = ?, locale_id = ? WHERE user_id = ? AND id = ?;";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, task.getName());    
        
            if (task.getDescription() != null) statement.setString(2, task.getDescription());
            if (task.getExecutedAt() != null) statement.setString(3, task.getExecutedAt().toString());
            if (task.getFinishedAt() != null) statement.setString(4, task.getFinishedAt().toString());
            if (task.getLocale() != null) statement.setInt(5, task.getLocale().getId());


            statement.setInt(6, userId);
            statement.setInt(7, task.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as tarefas associadas a uma data específica
    public List<Task> listByDate(LocalDate date) {
        // Cria uma lista para armazenar as tarefas retornadas
        List<Task> tasks = new ArrayList<>();
        // SQL para selecionar todas as tarefas
        String sql = "SELECT *, localizacoes.name AS locale_name, localizacoes.id AS locale_id, localizacoes.user_id AS locale_user_id FROM tarefas LEFT JOIN localizacoes ON tarefas.locale_id = localizacoes.id WHERE executed_at = '" + date + "';";

        
        try (Statement stmt = connection.createStatement();
             // Executa a query e obtém os resultados
             ResultSet rs = stmt.executeQuery(sql)) {
            // Enquanto houver resultados, processa cada linha retornada
            while (rs.next()) {
                // Cria um objeto Task a partir dos dados retornados pelo banco de dados
                Task task = new Task(
                        rs.getInt("id"), // ID da tarefa
                        rs.getObject("locale_id") != null ? new Locale(rs.getInt("locale_user_id"), rs.getString("locale_name"), rs.getString("locale_location")) : null, // ID da localização
                        rs.getString("name"), // Nome da tarefa
                        rs.getString("description"), // Descrição da tarefa
                        rs.getDate("executed_at") != null ? rs.getDate("executed_at").toLocalDate() : null, // Data de execução
                        rs.getDate("finished_at") != null ? rs.getDate("finished_at").toLocalDate() : null, // Data de conclusão
                        rs.getBoolean("completed")// Status de conclusão
                );
                // Adiciona a tarefa à lista
                tasks.add(task);
            }
        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }

        // Retorna a lista de tarefas
        return tasks;
    }

    public List<Task> listAll(int userId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT tarefas.*, localizacoes.name AS locale_name, localizacoes.id AS locale_id, localizacoes.user_id AS locale_user_id, localizacoes.location AS locale_location FROM tarefas LEFT JOIN localizacoes ON tarefas.locale_id = localizacoes.id WHERE tarefas.user_id = " + userId + ";";

        try (Statement stmt = connection.createStatement();
             // Executa a query e obtém os resultados
             ResultSet rs = stmt.executeQuery(sql)) {
            // Enquanto houver resultados, processa cada linha retornada
            while (rs.next()) {
                // Cria um objeto Task a partir dos dados retornados
                Task task = new Task(
                        rs.getInt("id"), // ID da tarefa
                        rs.getObject("locale_id") != null ? new Locale(rs.getInt("locale_user_id"), rs.getString("locale_name"), rs.getString("locale_location")) : null, // ID da localização
                        rs.getString("name"), // Nome da tarefa
                        rs.getString("description"), // Descrição da tarefa
                        rs.getDate("executed_at") != null ? rs.getDate("executed_at").toLocalDate() : null, // Data de execução
                        rs.getDate("finished_at") != null ? rs.getDate("finished_at").toLocalDate() : null, // Data de conclusão
                        rs.getBoolean("completed") // Status de conclusão
                );
                // Adiciona a tarefa à lista
                tasks.add(task);
            }
        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }

        // Retorna a lista de tarefas
        return tasks;
    }

    public void setCompletedTask(int taskId, int userId, boolean value) throws SQLException {
        String query = "UPDATE tarefas SET completed = ? WHERE user_id = ? AND id = ?;";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setBoolean(1, value);

            statement.setInt(2, userId);
            statement.setInt(3, taskId);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int taskId, int userId) throws SQLException {
        String query = "DELETE FROM tarefas WHERE user_id = ? AND id = ?;";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, userId);
            statement.setInt(2, taskId);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
