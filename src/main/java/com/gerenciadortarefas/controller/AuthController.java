package com.gerenciadortarefas.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AuthController {

    @FXML
    private StackPane authContainer;

    private Stage stage;

    @FXML
    public void initialize() {
        // Carregar a tela de login
        loadLogin();
    }

    private void loadView(String view, Object controller) {
        try {
           FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/gerenciadortarefas/view/" + view));
                fxmlLoader.setController(controller);
                Parent root = fxmlLoader.load();

            authContainer.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();

            // TODO: handle exception
        }
        // Implementar lógica para carregar diferentes FXMLs
    }

    public void loadLogin() {
        AuthLoginController authLoginController = new AuthLoginController();
        authLoginController.setAuthController(this);
        loadView("authLogin.fxml", authLoginController);
    }

    public void loadRegister() {
        AuthRegisterController authRegisterController = new AuthRegisterController();
        authRegisterController.setAuthController(this);
        loadView("authRegister.fxml", authRegisterController);
    }

    @FXML
    private void switchToRegister() {
        loadRegister();
    }

    @FXML
    private void switchToLogin() {
        loadLogin();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void openHome(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gerenciadortarefas/view/home.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
