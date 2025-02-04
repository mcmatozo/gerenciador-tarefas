package com.gerenciadortarefas.controller; // Define o pacote onde a classe está localizada

// Importações necessárias para o funcionamento do JavaFX e do serviço de tarefas
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

/**
 * Controlador do componente de tarefa (TaskComponent).
 * Este controlador gerencia a exibição e edição de uma tarefa na interface.
 */
public class TaskComponentController implements Initializable {

    @FXML
    private CheckBox completedCheckbox; // Caixa de seleção para marcar a tarefa como concluída

    @FXML
    private Text title; // Exibe o título da tarefa

    @FXML
    private Text description; // Exibe a descrição da tarefa

    private Task task; // Objeto que representa a tarefa associada a este componente
    private TaskService service; // Serviço responsável por gerenciar as tarefas

    /**
     * Método chamado automaticamente pelo JavaFX ao carregar a interface.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Método pode ser utilizado para configurações iniciais se necessário
    }

    /**
     * Configura os dados da tarefa na interface.
     * @param task Objeto da tarefa a ser exibida
     */
    public void setTaskData(Task task) {
        this.task = task;
        this.title.setText(task.getName()); // Define o título da tarefa

        // Verifica se a descrição está vazia. Se estiver, oculta o campo
        if (task.getDescription().isEmpty()) {
            this.description.setVisible(false);
        } else {
            this.description.setText(task.getDescription());
        }

        // Define o estado do checkbox conforme o status da tarefa
        completedCheckbox.setSelected(task.isCompleted());
        
        // Adiciona um listener para detectar mudanças no checkbox
        completedCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            task.setCompleted(newValue); // Atualiza o status da tarefa
            updateTaskTitleStyle(newValue); // Atualiza o estilo do título
            service.saveTask(); // Salva as alterações da tarefa
        });

        // Atualiza o estilo do título conforme o estado da tarefa (riscado se concluída)
        updateTaskTitleStyle(task.isCompleted());
    }

    /**
     * Atualiza o estilo do título da tarefa, riscando quando concluída.
     * @param completed Indica se a tarefa está concluída ou não
     */
    private void updateTaskTitleStyle(boolean completed) {
        if (completed) {
            title.setStyle("-fx-strikethrough: true;"); // Aplica o efeito de texto riscado
        } else {
            title.setStyle("-fx-strikethrough: false;"); // Remove o efeito de texto riscado
        }
    }

    /**
     * Define o serviço responsável por gerenciar as tarefas.
     * @param service Instância do serviço de tarefas
     */
    public void setService(TaskService service) {
        this.service = service;
    }

    /**
     * Abre o modal de edição da tarefa ao clicar sobre ela.
     * @param event Evento de clique do mouse
     */
    @FXML
    void handleOpenEditModal(MouseEvent event) {
        try {
            // Carrega o arquivo FXML do modal de edição de tarefa
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/com/gerenciadortarefas/view/taskComponentModalEdit.fxml"));
            Parent root = fxmlLoader.load();

            // Obtém o controlador do modal e passa os dados da tarefa
            TaskComponentModalEditController controller = fxmlLoader.getController();
            controller.setService(service);
            controller.setTaskData(task);

            // Cria e configura a nova janela do modal
            Stage stage = new Stage();
            stage.setTitle("Editar Tarefa");
            stage.initModality(Modality.APPLICATION_MODAL); // Torna o modal bloqueante
            stage.setScene(new Scene(root));
            stage.show(); // Exibe o modal

        } catch (Exception e) {
            e.printStackTrace(); // Exibe erros caso o modal não consiga ser aberto
        }
    }
}
