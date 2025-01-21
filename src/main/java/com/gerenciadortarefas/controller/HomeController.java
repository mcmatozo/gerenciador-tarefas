package com.gerenciadortarefas.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.service.TaskService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public HomeController() {
        service = new TaskService();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        setTasks();// chama o metodo pra ser executado
    }

    private void setTasks() {
        // List<Task> tasks = List.of(
        // new Task("Arrumar o quarto", "Eu preciso arrumar o quarto até o dia 29"),
        // new Task("Estudar matemática", "Revisar álgebra para a prova de
        // sexta-feira"),
        // new Task("Comprar mantimentos", "Ir ao mercado e comprar frutas e verduras"),
        // new Task("Ler um livro", "Ler o capítulo 5 de 'O Senhor dos Anéis'"),
        // new Task("Fazer exercícios", "Praticar 30 minutos de exercícios físicos"),
        // new Task("Limpar a cozinha", "Lavar a louça e organizar os armários"),
        // new Task("Responder e-mails", "Responder os e-mails pendentes do trabalho"),
        // new Task("Planejar a semana", "Organizar as tarefas e compromissos da próxima
        // semana"),
        // new Task("Revisar código", "Revisar o código do projeto antes do deploy"),
        // new Task("Assistir a um filme", "Assistir ao novo lançamento no cinema"),
        // new Task("Pagar contas", "Pagar a conta de luz e a fatura do cartão"),
        // new Task("Caminhar no parque", "Fazer uma caminhada no parque para relaxar"),
        // new Task("Estudar Java", "Praticar programação com Java e estudar padrões de
        // design"),
        // new Task("Organizar os papéis", "Separar documentos importantes e reciclar
        // papéis inúteis"),
        // new Task("Visitar os avós", "Passar a tarde com os avós e ajudar no que for
        // necessário"),
        // new Task("Fazer backup", "Fazer backup dos arquivos importantes no disco
        // externo"),
        // new Task("Aprender uma receita nova", "Cozinhar um prato novo para o jantar
        // de sábado"),
        // new Task("Limpar o carro", "Lavar e aspirar o carro no final de semana"),
        // new Task("Assistir a uma palestra online", "Participar do evento de
        // tecnologia às 19h"),
        // new Task("Planejar as férias", "Pesquisar destinos e fazer um orçamento para
        // viajar"));

        for (Task task : service.getTask()) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/gerenciadortarefas/view/taskComponent.fxml"));
                Node taskNode = loader.load();
                TaskComponentController controller = loader.getController();
                controller.setTaskData(task.getName(), task.getDescription());

                taskContainer.getChildren().add(taskNode); // CRIOU Lista de tarefas, instanciou isso futuramente, criou
                                                           // laço de repeticao, cria e instancia o componente e coloca
                                                           // o titulo e a desc da tarefa recem criada

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleOpenTaskModal(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/com/gerenciadortarefas/view/taskComponentModal.fxml"));
            Parent root = fxmlLoader.load();

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
