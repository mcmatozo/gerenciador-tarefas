package com.gerenciadortarefas.repositories;

import java.sql.SQLException;
import java.util.List;

import com.gerenciadortarefas.daos.TaskDAO;
import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.model.UserSession;

public class TaskRepository {

    // Instância do DAO de tarefas, responsável por interagir com o banco de dados
    private TaskDAO taskDAO;

    // Construtor que inicializa o TaskDAO
    public TaskRepository() {
        try {
            // Tenta criar a instância do TaskDAO. O TaskDAO é responsável pela comunicação com o banco de dados.
            this.taskDAO = new TaskDAO();
        } catch (SQLException e) {
            // Caso ocorra um erro ao tentar conectar ao banco de dados, ele será capturado e impresso
            e.printStackTrace();
        }
    }

    // Método para salvar uma nova tarefa no banco de dados
    public void save(Task task) {
        try {
            // Obtém o ID do usuário logado através da classe UserSession
            int userId = UserSession.getInstance().getUser().getId();
            
            // Chama o método 'create' do TaskDAO para persistir a tarefa no banco
            taskDAO.create(task, userId);
        } catch (SQLException e) {
            // Caso ocorra um erro ao tentar salvar a tarefa, o erro é capturado e impresso
            e.printStackTrace();
        }
    }

    // Método para listar todas as tarefas do banco de dados
    public List<Task> list() {
        // Retorna a lista de todas as tarefas usando o método 'listAll' do TaskDAO
        return taskDAO.listAll();
    }
}
