package com.gerenciadortarefas.controller; // Define o pacote onde esta classe está localizada

// Importação do serviço de autenticação
import com.gerenciadortarefas.service.AuthService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador responsável por gerenciar a tela de registro de usuários.
 */
public class AuthRegisterController {

    @FXML
    private Button registerButton; // Botão para registrar um novo usuário

    @FXML
    private TextField userEmail; // Campo de entrada para o e-mail do usuário

    @FXML
    private TextField userName; // Campo de entrada para o nome do usuário

    @FXML
    private PasswordField userPassword; // Campo de entrada para a senha do usuário

    private AuthController authController; // Referência ao controlador de autenticação principal

    @FXML
    private Label switchToLoginLabel; // Label que permite alternar para a tela de login

    private AuthService service; // Serviço de autenticação

    /**
     * Método acionado ao clicar no botão de registro.
     * Captura os dados inseridos pelo usuário e chama o serviço para registrar uma nova conta.
     *
     * @param event Evento acionado pelo clique no botão de registro.
     */
    @FXML
    public void handleRegister(ActionEvent event) {
        // Obtém os valores digitados nos campos de nome, e-mail e senha
        String email = userEmail.getText();
        String name = userName.getText();
        String password = userPassword.getText();

        // Chama o serviço para registrar o novo usuário
        service.register(name, email, password);
    }

    /**
     * Construtor da classe.
     * Inicializa o serviço de autenticação.
     */
    public AuthRegisterController() {
        this.service = new AuthService();
    }

    /**
     * Define a referência para o controlador principal de autenticação.
     *
     * @param authController O controlador de autenticação principal.
     */
    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    /**
     * Alterna para a tela de login quando o usuário clica no label correspondente.
     */
    @FXML
    private void switchToLogin() {
        if (authController != null) {
            authController.loadLogin();
        }
    }
}
