package com.gerenciadortarefas.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.model.UserSession;
import com.gerenciadortarefas.service.TaskService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    @FXML
    private StackPane rootPane;
    @FXML
    private VBox taskContainer;
    @FXML
    private Button addTaskButton;
    private TaskService service;

    @FXML
    private Label welcomeUserName;
    

    //@FXML
    //private Label dateLabel;

    //@FXML //logica pra atualizar a data
    //public void initialize() {
    //    updateDateLabel();
    //}

    //private void updateDateLabel() {
    //    LocalDate today = LocalDate.now();
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
     //   String formattedDate = today.format(formatter);
     //   dateLabel.setText("Dia " + formattedDate);
    //}

    //@FXML
    //private void handleOpenTaskModal() {
    //    // Lógica para abrir o modal de criação de tarefas
//}
    
    public HomeController() {
        service = new TaskService();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        welcomeUserName.setText("Bem-vindo, " + UserSession.getInstance().getUser().getName());

        setTasks();// chama o metodo pra ser executado
    }

    void setTasks() {
        // Ouvir as mudanças na lista de tarefas
        service.initObservable().addListener((javafx.collections.ListChangeListener<Task>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Task task : change.getAddedSubList()) {
                        renderTask(task); // Renderiza novas tarefas
                    }
                } else if (change.wasRemoved()) {
                    // Caso alguma tarefa seja removida (você pode querer tratar esse caso)
                    taskContainer.getChildren().clear(); // Limpar as tarefas atuais
                    for (Task task : service.getTasks()) {
                        renderTask(task); // Re-renderiza todas as tarefas
                    }
                }
            }
        });
    
        // Carregar as tarefas inicialmente
        for (Task task : service.getTasks()) {
            renderTask(task);
        }
    }
    
    private void renderTask(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/gerenciadortarefas/view/taskComponent.fxml"));
            Node taskNode = loader.load();
            TaskComponentController controller = loader.getController();
            controller.setService(service);
            controller.setTaskData(task);
            taskContainer.getChildren().add(taskNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
public void handleOpenTaskModal(ActionEvent event) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/gerenciadortarefas/view/taskComponentModal.fxml"));
        Parent root = fxmlLoader.load();

        TaskComponentModalController controller = fxmlLoader.getController();
        controller.setService(service);
        controller.setHomeController(this);  // Passa o controlador principal para o modal

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
