package com.gerenciadortarefas.controller; // Define o pacote onde a classe está localizada

// Importações necessárias para o JavaFX, manipulação de eventos e modelagem da tarefa
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

/**
 * Controlador do modal para criação de tarefas. 
 * Este controlador permite ao usuário preencher os dados da tarefa e salvar.
 */
public class TaskComponentModalController implements Initializable {

    @FXML
    private TextArea description; // Campo de texto para a descrição da tarefa

    @FXML
    private DatePicker executedAt; // Data de execução da tarefa

    @FXML
    private DatePicker finishedAt; // Data de término da tarefa

    @FXML
    private Button saveButton; // Botão para salvar a tarefa

    @FXML
    private TextField title; // Campo de texto para o título da tarefa

    private TaskService service; // Serviço que gerencia as tarefas
    private HomeController homeController;  // Referência para o controlador principal, HomeController

    /**
     * Método chamado automaticamente pelo JavaFX ao carregar a interface.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Método pode ser utilizado para inicializar valores ou configurar os controles, se necessário
    }

    /**
     * Cria uma nova tarefa com os dados inseridos no modal.
     * Este método é chamado quando o botão de salvar é pressionado.
     * @param event Evento de clique no botão "salvar"
     */
    @FXML
    public void createTask(ActionEvent event) {
        // Cria uma nova instância de Task utilizando os dados inseridos pelo usuário
        Task task = new Task(
                title.getText(),              // Título da tarefa
                description.getText(),        // Descrição da tarefa
                executedAt.getValue(),        // Data de execução
                finishedAt.getValue()         // Data de término
        );

        service.addTask(task); // Adiciona a nova tarefa ao serviço que gerencia as tarefas

        // Se o HomeController estiver disponível, atualiza a lista de tarefas na tela principal
        if (homeController != null) {
            homeController.setTasks();  // Atualiza a lista de tarefas no HomeController
        }

        // Fecha o modal após salvar a tarefa
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close(); // Fecha a janela do modal
    }

    /**
     * Define o serviço de gerenciamento de tarefas.
     * @param service Instância do TaskService
     */
    public void setService(TaskService service) {
        this.service = service;
    }

    /**
     * Define a referência para o controlador principal (HomeController).
     * @param homeController Instância do HomeController
     */
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;  // Define a referência para o HomeController
    }
}
