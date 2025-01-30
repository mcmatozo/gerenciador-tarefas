package com.gerenciadortarefas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.service.TaskService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskComponentModalController implements Initializable {

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
    private HomeController homeController;  // Referência para o HomeController

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Inicialização, se necessário
    }

    @FXML
    public void createTask(ActionEvent event) {
        Task task = new Task(
                title.getText(),
                description.getText(),
                executedAt.getValue(),
                finishedAt.getValue());

        service.addTask(task); // Adiciona a tarefa ao serviço

        // Atualiza a tela principal
        if (homeController != null) {
            homeController.setTasks();  // Atualiza a lista de tarefas no HomeController
        }

        // Fecha o modal
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void setService(TaskService service) {
        this.service = service;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;  // Define a referência para o HomeController
    }
}
