package com.gerenciadortarefas.controller; // Define o pacote onde a classe está localizada

// Importações de modelos e serviços necessários para autenticação
import com.gerenciadortarefas.model.User; // Representa um usuário
import com.gerenciadortarefas.model.UserSession; // Gerencia a sessão do usuário logado
import com.gerenciadortarefas.service.AuthService; // Serviço responsável por autenticação

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador responsável por gerenciar a tela de login.
 */
public class AuthLoginController {

    @FXML
    private Button loginButton; // Botão de login

    @FXML
    private PasswordField password; // Campo de senha

    @FXML
    private TextField userEmail; // Campo de e-mail

    private AuthController authController; // Referência ao controlador de autenticação principal
    private AuthService service; // Serviço de autenticação

    @FXML
    private Label switchToRegisterLabel; // Label que permite alternar para a tela de registro

    /**
     * Método chamado ao clicar no botão de login.
     * Realiza a autenticação do usuário e, se bem-sucedida, abre a tela principal.
     *
     * @param event Evento acionado pelo clique no botão de login.
     */
    @FXML
    void handleLogin(ActionEvent event) {
       try {
        setLoading(true);

        String email = userEmail.getText();
        String userPassword = password.getText();

        User user = service.login(email, userPassword);
        UserSession.getInstance().setUser(user);
        
        setLoading(false);
        
        authController.openHome();
       } catch (RuntimeException e) {
            // TODO: Adicionar lógica para exibir mensagens de erro ao usuário
        }
    }

    /**
     * Construtor da classe. Inicializa o serviço de autenticação.
     */
    public AuthLoginController() {
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
     * Alterna para a tela de registro quando o usuário clica no label correspondente.
     */
    @FXML
    private void switchToRegister() {
        if (authController != null) {
            authController.loadRegister();
        }
    }

    private void setLoading(boolean value) {
        if (value) {
            loginButton.setText("Carregando...");
            loginButton.setDisable(true);

            return;
        }

        loginButton.setText("Login");
        loginButton.setDisable(false);
    }

}
