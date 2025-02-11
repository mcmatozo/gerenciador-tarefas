package com.gerenciadortarefas.controller; // Define o pacote onde a classe está localizada

// Importações necessárias para o JavaFX, manipulação de eventos e modelagem da tarefa
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Locale;
import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.service.LocaleService;
import com.gerenciadortarefas.service.TaskService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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


    @FXML
    private Button createLocaleButton;

    @FXML
    private ComboBox<Locale> localeSelect;
    /**
     * Método chamado automaticamente pelo JavaFX ao carregar a interface.
     */
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

        localeSelect.setOnShowing(event -> loadLocations()); // toda vez que voce clicar no select, ele vai chamar o banco
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
                title.getText(),
                description.getText(),
                executedAt.getValue(),
                finishedAt.getValue(),
                localeSelect.getSelectionModel().getSelectedItem() // Get the selected locale id
        );

        service.addTask(task); // Adiciona a nova tarefa ao serviço que gerencia as tarefas

        // Atualiza a tela principal
        // if (homeController != null) {
        //     homeController.setTasks();  // Atualiza a lista de tarefas no HomeController
        // }

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

    @FXML
    public void onCreateLocale(ActionEvent event) {
         
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/com/gerenciadortarefas/view/localeComponentModal.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Criar Novo Local");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadLocations(){
        
        LocaleService localeService = new LocaleService();
        List<Locale> list = localeService.list();
        ObservableList<Locale> locations = FXCollections.observableArrayList(list);
        localeSelect.setItems(locations);
    }
}
