package com.gerenciadortarefas.controller; // Define o pacote onde essa classe está localizada

// Importações do JavaFX para manipular a interface gráfica (FXML, cenas e elementos visuais)
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controlador responsável pela autenticação do usuário.
 * Gerencia a exibição das telas de login e registro.
 */
public class AuthController {

    @FXML
    private StackPane authContainer; // Um contêiner onde as telas de login e registro serão carregadas

    private Stage stage; // Referência ao Stage principal da aplicação

    /**
     * Método de inicialização chamado automaticamente pelo JavaFX após o carregamento do FXML.
     * Aqui, carregamos a tela de login por padrão.
     */
    @FXML
    public void initialize() {
        loadLogin(); // Carrega a tela de login ao iniciar o controlador
    }

    /**
     * Carrega uma tela FXML dentro do authContainer.
     *
     * @param view O nome do arquivo FXML a ser carregado.
     * @param controller O controlador correspondente à tela.
     */
    private void loadView(String view, Object controller) {
        try {
            // Cria um carregador de FXML e define o caminho do arquivo de interface
            FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/gerenciadortarefas/view/" + view)
            );

            // Define manualmente o controlador da nova tela
            fxmlLoader.setController(controller);

            // Carrega o layout definido no FXML
            Parent root = fxmlLoader.load();

            // Substitui o conteúdo do authContainer pela nova tela carregada
            authContainer.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro no console para depuração

            // TODO: Tratar a exceção de forma mais adequada (exibir alerta para o usuário, etc.)
        }
    }

    /**
     * Carrega a tela de login dentro do authContainer.
     */
    public void loadLogin() {
        AuthLoginController authLoginController = new AuthLoginController(); // Cria um controlador para a tela de login
        authLoginController.setAuthController(this); // Passa a referência do AuthController para o controlador de login
        loadView("authLogin.fxml", authLoginController); // Chama o método genérico para carregar a tela
    }

    /**
     * Carrega a tela de registro dentro do authContainer.
     */
    public void loadRegister() {
        AuthRegisterController authRegisterController = new AuthRegisterController(); // Cria um controlador para a tela de registro
        authRegisterController.setAuthController(this); // Passa a referência do AuthController para o controlador de registro
        loadView("authRegister.fxml", authRegisterController); // Chama o método genérico para carregar a tela
    }

    /**
     * Método acionado ao clicar em "Registrar-se" na tela de login.
     * Alterna para a tela de registro.
     */
    @FXML
    private void switchToRegister() {
        loadRegister();
    }

    /**
     * Método acionado ao clicar em "Já tem uma conta? Entrar" na tela de registro.
     * Alterna para a tela de login.
     */
    @FXML
    private void switchToLogin() {
        loadLogin();
    }

    /**
     * Define o stage principal da aplicação.
     *
     * @param stage O objeto Stage que representa a janela principal.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Abre a tela principal da aplicação após o login bem-sucedido.
     */
    public void openHome() {
        try {
            // Carrega a tela principal (home)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gerenciadortarefas/view/home.fxml"));
            Parent root = fxmlLoader.load();

            // Cria uma nova cena com o layout carregado
            Scene scene = new Scene(root);

            // Define a nova cena no Stage e exibe a janela
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro no console para depuração
        }
    }
}
