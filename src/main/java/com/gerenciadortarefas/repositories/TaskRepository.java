package com.gerenciadortarefas.repositories;

import java.sql.SQLException;
import java.util.List;

import com.gerenciadortarefas.daos.TaskDAO;
import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.model.UserSession;

public class TaskRepository {

    private TaskDAO taskDAO;

    public TaskRepository() {
        
        try {
            this.taskDAO = new TaskDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Task task) {

        try {
            int userId = UserSession.getInstance().getUser().getId();
            taskDAO.create(task, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> list() {
        return taskDAO.listAll();
    }
    
}
