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

    // Construtor que inicializa a conexão com o banco de dados
    public TaskDAO() throws SQLException {
        // Obtém a conexão com o banco através da classe DatabaseConnection
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para criar uma nova tarefa no banco de dados
    public void create(Task task, int userId) throws SQLException {
        // SQL para inserção de uma nova tarefa na tabela "tarefas"
        String query = "INSERT INTO tarefas (user_id, name, description, status, executed_at, finished_at) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            // Preparação da instrução SQL com placeholders para os parâmetros
            PreparedStatement statement = connection.prepareStatement(query);
            // Atribui os valores aos parâmetros da query
            statement.setInt(1, userId); // ID do usuário (relacionando a tarefa a um usuário específico)
            statement.setString(2, task.getName()); // Nome da tarefa
            statement.setString(3, task.getDescription()); // Descrição da tarefa
            statement.setString(4, null); // Status da tarefa (no momento é "null", isso deveria ser tratado de forma adequada)
            statement.setString(5, task.getExecutedAt().toString()); // Data de execução da tarefa
            statement.setString(6, task.getFinishedAt().toString()); // Data de conclusão da tarefa
            // Executa a query para inserir os dados no banco
            statement.executeUpdate();
        } catch (Exception e) {
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        } finally {
            // Garante que a conexão será fechada após a execução
            connection.close();
        }
    }

    // Método vazio, que parece ser um ponto de partida para obter tarefas específicas
    public void get() {
        // Este método poderia ser implementado para buscar uma tarefa por ID ou algum outro critério
    }

    // Método para listar todas as tarefas associadas a uma data específica
    public List<Task> listByDate(LocalDate date) {
        // Cria uma lista para armazenar as tarefas retornadas
        List<Task> tasks = new ArrayList<>();
        // SQL para selecionar todas as tarefas
        String sql = "SELECT * FROM tarefas ";

        try (Statement stmt = connection.createStatement();
             // Executa a query e obtém os resultados
             ResultSet rs = stmt.executeQuery(sql)) {
            // Enquanto houver resultados, processa cada linha retornada
            while (rs.next()) {
                // Cria um objeto Task a partir dos dados retornados pelo banco de dados
                Task task = new Task(
                        rs.getInt("id"), // ID da tarefa
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

    // Método para listar todas as tarefas (sem filtro por data)
    public List<Task> listAll() {
        // Cria uma lista para armazenar as tarefas
        List<Task> tasks = new ArrayList<>();
        // SQL para selecionar todas as tarefas
        String sql = "SELECT * FROM tarefas ";

        try (Statement stmt = connection.createStatement();
             // Executa a query e obtém os resultados
             ResultSet rs = stmt.executeQuery(sql)) {
            // Enquanto houver resultados, processa cada linha retornada
            while (rs.next()) {
                // Cria um objeto Task a partir dos dados retornados
                Task task = new Task(
                        rs.getInt("id"), // ID da tarefa
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
    
}
