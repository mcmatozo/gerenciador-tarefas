package com.gerenciadortarefas.controller; // Define o pacote onde a classe está localizada

// Importações necessárias para manipulação de arquivos, recursos e interface gráfica
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Task; // Modelo que representa uma tarefa
import com.gerenciadortarefas.model.UserSession; // Classe que gerencia a sessão do usuário
import com.gerenciadortarefas.service.TaskService; // Serviço responsável por gerenciar tarefas

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

/**
 * Controlador responsável por gerenciar a tela inicial da aplicação.
 * Exibe as tarefas do usuário e permite adicionar novas tarefas.
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane rootPane; // Container principal da interface

    @FXML
    private VBox taskContainer; // Container onde as tarefas são listadas

    @FXML
    private Button addTaskButton; // Botão para adicionar uma nova tarefa

    private TaskService service; // Serviço responsável pelo gerenciamento das tarefas

    @FXML
    private Label welcomeUserName; // Label que exibe o nome do usuário logado

    /**
     * Construtor da classe.
     * Inicializa o serviço de gerenciamento de tarefas.
     */
    public HomeController() {
        service = new TaskService();
    }

    /**
     * Método de inicialização chamado automaticamente pelo JavaFX ao carregar a tela.
     * Exibe o nome do usuário e carrega as tarefas do usuário.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Define o nome do usuário logado na label de boas-vindas
        welcomeUserName.setText("Bem-vindo, " + UserSession.getInstance().getUser().getName());

        // Chama o método para carregar as tarefas do usuário
        setTasks();
    }

    /**
     * Configura a lista de tarefas do usuário, ouvindo mudanças na lista para
     * atualização automática da interface.
     */
    void setTasks() {
        // Adiciona um ouvinte para detectar mudanças na lista de tarefas
        service.initObservable().addListener((javafx.collections.ListChangeListener<Task>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    // Se uma nova tarefa foi adicionada, renderiza na tela
                    for (Task task : change.getAddedSubList()) {
                        renderTask(task);
                    }
                } else if (change.wasRemoved()) {
                    // Se uma tarefa foi removida, recarrega toda a lista
                    taskContainer.getChildren().clear();
                    for (Task task : service.getTasks()) {
                        renderTask(task);
                    }
                }
            }
        });
    

        loadTasks();
    }

    public void loadTasks() {
        taskContainer.getChildren().clear();

        // Carregar as tarefas inicialmente
        for (Task task : service.getTasks()) {
            renderTask(task);
        }
    }

    /**
     * Renderiza uma tarefa na interface gráfica.
     *
     * @param task A tarefa a ser exibida na tela.
     */
    private void renderTask(Task task) {
        try {
            // Carrega o componente FXML responsável por exibir uma única tarefa
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/gerenciadortarefas/view/taskComponent.fxml"));
            Node taskNode = loader.load();

            // Obtém o controlador do componente e define os dados da tarefa
            TaskComponentController controller = loader.getController();
            controller.setService(service);
            controller.setTaskData(task);
            controller.setHomeController(this);

            taskContainer.getChildren().add(taskNode);
        } catch (IOException e) {
            e.printStackTrace(); // Exibe erros caso ocorra falha no carregamento do FXML
        }
    }

    /**
     * Abre um modal para criação de uma nova tarefa.
     *
     * @param event O evento acionado pelo clique no botão de adicionar tarefa.
     */
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
