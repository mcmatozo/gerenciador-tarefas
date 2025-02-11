package com.gerenciadortarefas.controller; // Define o pacote onde a classe está localizada

import com.gerenciadortarefas.singleton.StageSingleton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador do componente de menu lateral (Side Menu).
 * Permite a expansão e recolhimento do menu ao clicar no botão.
 */
public class SideMenuComponentController {

    @FXML
    private VBox sideMenu; // Elemento do menu lateral, um contêiner vertical

    @FXML
    private Button menuButton; // Botão para expandir/recolher o menu

    private boolean isExpanded = false; // Variável para controlar o estado do menu

    /**
     * Método chamado automaticamente pelo JavaFX ao carregar a tela.
     */
    @FXML
    private Button logoutButton;

    @FXML
    private Button localeButton;

    @FXML
    private Button taskButton;

    @FXML
    public void taskAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gerenciadortarefas/view/home.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = StageSingleton.getInstance().getStage();
            
            Scene scene = new Scene(root, 900, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void localeAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gerenciadortarefas/view/localeList.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = StageSingleton.getInstance().getStage();
            
            Scene scene = new Scene(root, 900, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        localeButton.setVisible(false);
        logoutButton.setVisible(false);
        taskButton.setVisible(false);
        sideMenu.setPrefWidth(50);
    }

    /**
     * Alterna o tamanho do menu lateral entre expandido e recolhido.
     * É acionado ao clicar no botão do menu.
     */
    @FXML
    public void toggleMenu() {
        if (isExpanded) {
            logoutButton.setVisible(false);
            localeButton.setVisible(false);
            taskButton.setVisible(false);
            sideMenu.setPrefWidth(50);
        } else {
            logoutButton.setVisible(true);
            localeButton.setVisible(true);
            taskButton.setVisible(true);
            sideMenu.setPrefWidth(250);
        }
        isExpanded = !isExpanded; // Alterna o estado do menu
    }

    @FXML
    public void logoutAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gerenciadortarefas/view/auth.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = StageSingleton.getInstance().getStage();
            
            Scene scene = new Scene(root, 900, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
