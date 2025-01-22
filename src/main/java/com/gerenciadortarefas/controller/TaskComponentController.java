package com.gerenciadortarefas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.service.TaskService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskComponentController implements Initializable {

    @FXML
    private CheckBox completedCheckbox;

    @FXML // ta apontando pra view
    private Text title;

    @FXML
    private Text description;
    private Task task;
    private TaskService service;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    public void setTaskData(Task task) {

        this.task = task;
        this.title.setText(task.getName());

        if (task.getDescription().isEmpty()) {
            this.description.setVisible(false);
        } else {
            this.description.setText(task.getDescription());
        }

        completedCheckbox.setSelected(task.isCompleted());// completedCheckbox é o id no scene builder
        completedCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            task.setCompleted(newValue);
            updateTaskTitleStyle(newValue);
            service.saveTask();
        });

        updateTaskTitleStyle(task.isCompleted());
    }

    private void updateTaskTitleStyle(boolean completed) {
        if (completed) {
            title.setStyle("-fx-strikethrough: true;");
        } else {
            title.setStyle("-fx-strikethrough: false;");
        }
    }

    public void setService(TaskService service) {
        this.service = service;
    }

    @FXML
    void handleOpenEditModal(MouseEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/com/gerenciadortarefas/view/taskComponentModalEdit.fxml"));
            Parent root = fxmlLoader.load();

            TaskComponentModalEditController controller = fxmlLoader.getController();
            controller.setService(service);
            controller.setTaskData(task);

            Stage stage = new Stage();
            stage.setTitle("Criar Nova Tarefa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
