package com.gerenciadortarefas.controller;


import com.gerenciadortarefas.service.AuthService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthRegisterController {

    @FXML
    private Button registerButton;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    private AuthController authController;

    @FXML
    private Label switchToLoginLabel;

    private AuthService service;

    @FXML
    public void handleRegister(ActionEvent event) {
        String email = userEmail.getText();
        String name = userName.getText();
        String password = userPassword.getText();

        service.register(name, email, password);
    }

    public AuthRegisterController() {
        this.service = new AuthService();
    }

    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    @FXML
    private void switchToLogin() {
        if (authController != null) {
            authController.loadLogin();
        }
    }

}
