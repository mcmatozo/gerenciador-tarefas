package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = new User(username, password);

        // Lógica de validação
        if (validateUser(user)) {
            showAlert("Login bem-sucedido!", "Bem-vindo, " + username + "!");
        } else {
            showAlert("Erro de Login", "Nome de usuário ou senha incorretos.", Alert.AlertType.ERROR);
        }
    }

    private boolean validateUser(User user) {
        // Exemplo de validação simples (poderia ser substituído por lógica de banco de dados)
        return "admin".equals(user.getUsername()) && "senha123".equals(user.getPassword());
    }

    private void showAlert(String title, String message) {
        showAlert(title, message, Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
