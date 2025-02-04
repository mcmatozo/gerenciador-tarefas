package com.gerenciadortarefas.controller; // Define o pacote onde a classe está localizada

// Importações do JavaFX para manipulação da interface gráfica
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

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
    public void initialize() {
        // Pode ser usado futuramente para configurar comportamentos iniciais
    }

    /**
     * Alterna o tamanho do menu lateral entre expandido e recolhido.
     * É acionado ao clicar no botão do menu.
     */
    @FXML
    public void toggleMenu() {
        if (isExpanded) {
            sideMenu.setPrefWidth(50); // Recolhe o menu para 50 pixels de largura
        } else {
            sideMenu.setPrefWidth(250); // Expande o menu para 250 pixels de largura
        }
        isExpanded = !isExpanded; // Alterna o estado do menu
    }
}
