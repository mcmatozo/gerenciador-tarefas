package com.gerenciadortarefas.repositories;

import java.sql.SQLException;

import com.gerenciadortarefas.daos.TaskDAO;
import com.gerenciadortarefas.model.Task;

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
            taskDAO.create(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void list() {

    }
    
}
