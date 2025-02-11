package com.gerenciadortarefas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    // Método responsável por criar as tabelas no banco de dados
    public static void createTables() {
        // Comando SQL para criar a tabela "tarefas"
        String createTaskSql = "CREATE TABLE IF NOT EXISTS tarefas ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"  // Coluna 'id' como chave primária e autoincremento
                + "user_id INT NOT NULL,"  // Coluna 'user_id' para armazenar a referência ao usuário
                + "FOREIGN KEY (user_id) REFERENCES usuarios(id),"  // Define a chave estrangeira para a tabela 'usuarios'
                + "locale_id INT,"  
                + "FOREIGN KEY (locale_id) REFERENCES localizacoes(id),"
                + "name VARCHAR(255) NOT NULL,"  // Coluna 'name' para armazenar o nome da tarefa
                + "description TEXT NOT NULL,"  // Coluna 'description' para armazenar a descrição da tarefa
                + "status VARCHAR(255),"  // Coluna 'status' para armazenar o status da tarefa
                + "completed BOOLEAN DEFAULT false,"  // Coluna 'completed' para indicar se a tarefa foi completada
                + "executed_at TIMESTAMP,"  // Coluna 'executed_at' para armazenar a data de execução da tarefa
                + "finished_at TIMESTAMP,"  // Coluna 'finished_at' para armazenar a data de finalização da tarefa
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"  // Coluna 'created_at' com valor padrão para a data de criação
                + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"  // Coluna 'updated_at' que é atualizada automaticamente
                + ")";

        // Comando SQL para criar a tabela "usuarios"
        String createUserSql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"  // Coluna 'id' como chave primária e autoincremento
                + "name VARCHAR(255) NOT NULL,"  // Coluna 'name' para armazenar o nome do usuário
                + "email VARCHAR(255) NOT NULL,"  // Coluna 'email' para armazenar o e-mail do usuário
                + "password VARCHAR(255) NOT NULL,"  // Coluna 'password' para armazenar a senha do usuário
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"  // Coluna 'created_at' com valor padrão para a data de criação
                + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"  // Coluna 'updated_at' que é atualizada automaticamente
                + ")";

        String createLocaleSql = "CREATE TABLE IF NOT EXISTS localizacoes ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"  // Coluna 'id' como chave primária e autoincremento
                + "user_id INT NOT NULL,"  // Coluna 'user_id' para armazenar a referência ao usuário
                + "FOREIGN KEY (user_id) REFERENCES usuarios(id),"
                + "name VARCHAR(255) NOT NULL,"  // Coluna 'name' para armazenar o nome do usuário
                + "location VARCHAR(255) NOT NULL,"  // Coluna 'email' para armazenar o e-mail do usuário
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"  // Coluna 'created_at' com valor padrão para a data de criação
                + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"  // Coluna 'updated_at' que é atualizada automaticamente
                + ")";


        // String createTaskLocaleSql = "CREATE TABLE IF NOT EXISTS tarefa_localizacoes ("
        //         + "id INT AUTO_INCREMENT PRIMARY KEY,"  // Coluna 'id' como chave primária e autoincremento
        //         + "task_id INT NOT NULL,"  // Coluna 'user_id' para armazenar a referência ao usuário
        //         + "FOREIGN KEY (task_id) REFERENCES tarefas(id),"
        //         + "locale_id INT NOT NULL,"  // Coluna 'user_id' para armazenar a referência ao usuário
        //         + "FOREIGN KEY (locale_id) REFERENCES localizacoes(id),"
        //         + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"  // Coluna 'created_at' com valor padrão para a data de criação
        //         + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"  // Coluna 'updated_at' que é atualizada automaticamente
        //         + ")";

        // Tentativa de conexão com o banco de dados e execução dos comandos SQL
        try (Connection conn = DatabaseConnection.getConnection();  // Obtém a conexão com o banco de dados
             Statement stmt = conn.createStatement()) {  // Cria um Statement para executar os comandos SQL

            // Executa os comandos SQL para criar as tabelas
            stmt.execute(createUserSql);  // Cria a tabela 'usuarios'
            stmt.execute(createTaskSql);  // Cria a tabela 'tarefas'
            stmt.execute(createLocaleSql);
            //stmt.execute(createTaskLocaleSql);

        } catch (SQLException e) {
            e.printStackTrace();  // Imprime o erro caso ocorra uma exceção
        }
    }

    // Método que poderia ser usado para inserir dados de exemplo no banco de dados (comentado)
    //public static void insertSampleData() {
    //    String sql = "INSERT INTO tarefas (name, description, status, completed, executed_at, finished_at) VALUES "
    //            + "('Tarefa 1', 'Descrição da Tarefa 1', 'Pendente', false, NULL, NULL),"
    //            + "('Tarefa 2', 'Descrição da Tarefa 2', 'Em Progresso', false, NULL, NULL),"
    //            + "('Tarefa 3', 'Descrição da Tarefa 3', 'Concluída', true, '2023-01-01 10:00:00', '2023-01-01 12:00:00')";
    //    try (Connection conn = DatabaseConnection.getConnection();
    //         Statement stmt = conn.createStatement()) {
    //       stmt.execute(sql);  // Executa o SQL para inserir os dados
    //    } catch (SQLException e) {
    //       e.printStackTrace();  // Imprime o erro caso ocorra uma exceção
    //    }
    //}

    // Método main para testar a criação das tabelas
    public static void main(String[] args) {
        createTables();  // Chama o método que cria as tabelas
    }
}
