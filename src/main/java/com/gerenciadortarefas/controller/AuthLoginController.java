package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.model.User;
import com.gerenciadortarefas.model.UserSession;
import com.gerenciadortarefas.service.AuthService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthLoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userEmail;

    private AuthController authController;
    private AuthService service;

    @FXML
    private Label switchToRegisterLabel;

    @FXML
    void handleLogin(ActionEvent event) {
       try {
        String email = userEmail.getText();
        String userPassword = password.getText();

        User user = service.login(email, userPassword);
        UserSession.getInstance().setUser(user);
        authController.openHome();
       } catch (RuntimeException e) {

       }
    }
    
    public AuthLoginController() {
        this.service = new AuthService();
    }
    
    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    @FXML
    private void switchToRegister() {
        if (authController != null) {
            authController.loadRegister();
        }
    }

}
