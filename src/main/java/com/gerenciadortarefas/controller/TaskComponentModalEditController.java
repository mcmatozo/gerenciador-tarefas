package com.gerenciadortarefas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.service.TaskService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskComponentModalEditController implements Initializable {

    @FXML
    private TextArea description; // Campo para a descrição da tarefa

    @FXML
    private DatePicker executedAt; // Data de execução da tarefa

    @FXML
    private DatePicker finishedAt; // Data de término da tarefa

    @FXML
    private Button saveButton; // Botão para salvar as alterações

    @FXML
    private Button removeButton;

    @FXML
    private TextField title;

    private TaskService service; // Serviço de gerenciamento de tarefas
    private Task task; // Tarefa que está sendo editada
    private HomeController homeController;  // Referência ao HomeController

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Inicialização, se necessário
        saveButton.setDisable(true);
        
        // Add listener to validate the title field
        title.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.trim().isEmpty()) {
                    title.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    saveButton.setDisable(true);
                } else {
                    title.setStyle(""); // Reset border
                    saveButton.setDisable(false);
                }
            }
        });
    }

    @FXML
    public void editTask(ActionEvent event) {
        try {
            // Verifica se o campo de título está vazio
            if (title.getText().trim().isEmpty()) {
                showError("O título da tarefa não pode estar vazio.");
                return;
            }

            // Atualiza os dados da tarefa com os valores editados pelo usuário
            task.setName(title.getText());
            task.setDescription(description.getText());
            task.setExecutedAt(executedAt.getValue());
            task.setFinishedAt(finishedAt.getValue());

            service.updateTask(task);
            homeController.loadTasks();

                // Fecha o modal após salvar as alterações
            closeModal();
        } catch (Exception e) {
            showError("Erro ao salvar a tarefa. Tente novamente.");
            e.printStackTrace();
        }
    }

    /**
     * Preenche os campos do formulário com os dados da tarefa atual.
     */
    public void setTaskData(Task task) {
        this.task = task;
        title.setText(task.getName());
        description.setText(task.getDescription());
        executedAt.setValue(task.getExecutedAt());
        finishedAt.setValue(task.getFinishedAt());
    }

    /**
     * Define o serviço de gerenciamento de tarefas.
     */
    public void setService(TaskService service) {
        this.service = service;
    }

    /**
     * Define a referência para o controlador principal (HomeController).
     */
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    /**
     * Fecha a janela do modal.
     */
    private void closeModal() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Exibe um alerta de erro para o usuário.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void removeTask(ActionEvent event) {
        service.removeTask(task);
        homeController.loadTasks();

        // Fecha o modal
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
