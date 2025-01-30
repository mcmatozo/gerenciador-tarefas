package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.service.TaskService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskComponentModalEditController {

    @FXML
    private TextArea description;

    @FXML
    private DatePicker executedAt;

    @FXML
    private DatePicker finishedAt;

    @FXML
    private Button saveButton;

    @FXML
    private TextField title;

    private TaskService service;
    private Task task;
    private HomeController homeController;  // Referência para o HomeController

    @FXML
    public void editTask(ActionEvent event) {
        // Atualiza os dados da tarefa
        task.setName(title.getText());
        task.setDescription(description.getText());
        task.setExecutedAt(executedAt.getValue());
        task.setFinishedAt(finishedAt.getValue());

        service.saveTask();  // Salva a tarefa no serviço

        // Atualiza a lista de tarefas na tela principal
        if (homeController != null) {
            homeController.setTasks();  // Atualiza a lista de tarefas no HomeController
        }

        // Fecha o modal
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void setTaskData(Task task) {
        this.task = task;
        title.setText(task.getName());
        description.setText(task.getDescription());
        executedAt.setValue(task.getExecutedAt());
        finishedAt.setValue(task.getFinishedAt());
    }

    public void setService(TaskService service) {
        this.service = service;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;  // Define a referência para o HomeController
    }
}
