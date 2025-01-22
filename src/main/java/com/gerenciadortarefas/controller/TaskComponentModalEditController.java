package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.service.TaskService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

    @FXML
    public void editTask(ActionEvent event) {
        task.setName(title.getText());
        task.setDescription(description.getText());
        task.setExecutedAt(executedAt.getValue());
        task.setFinishedAt(finishedAt.getValue());
        service.saveTask();
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close(); // fecha a aba quando clicar em salvar
    }

    public void setTaskData(Task task) {

        title.setText(task.getName());
        description.setText(task.getDescription());
        executedAt.setValue(task.getExecutedAt());
        finishedAt.setValue(task.getFinishedAt());
        this.task = task;
    }

    public void setService(TaskService service) {
        this.service = service;
    }

}
