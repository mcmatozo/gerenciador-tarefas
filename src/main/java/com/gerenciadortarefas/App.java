package com.gerenciadortarefas; // Define o pacote onde a classe está localizada

import java.io.IOException; // Importa a classe IOException para tratar erros de entrada e saída

import com.gerenciadortarefas.singleton.StageSingleton; // Importa o controlador de autenticação

import javafx.application.Application;
import javafx.fxml.FXMLLoader; // Importa a classe principal do JavaFX
import javafx.scene.Parent; // Importa o carregador de arquivos FXML
import javafx.scene.Scene; // Importa a classe Parent, que é a raiz da hierarquia de nós da cena
import javafx.stage.Stage; // Importa a classe Scene, que representa a tela

public class App extends Application { // Declara a classe App que herda de Application

    private static Scene scene; // Variável estática para armazenar a cena atual

    @Override
    public void start(Stage stage) throws IOException {
        // Método chamado automaticamente ao iniciar a aplicação

        // Carrega a interface gráfica a partir do arquivo auth.fxml
        FXMLLoader loader = loadFXML("view/auth"); // Chama o método loadFXML passando o nome do arquivo sem a extensão
        Parent root = loader.load(); // Carrega o conteúdo do arquivo FXML e retorna como um nó raiz

        
        scene = new Scene(root, 900, 500); // Ajuste o tamanho conforme necessário
        stage.setScene(scene);

        // Define o título da janela
        stage.setTitle("Tela de Login");

        // Centraliza a janela na tela do usuário
        stage.centerOnScreen();

        // Exibe a janela
        stage.show();

        StageSingleton.getInstance().setStage(stage);
    }

    // Método auxiliar para carregar arquivos FXML
    private FXMLLoader loadFXML(String fxml) throws IOException {
        // Cria um carregador de arquivos FXML baseado no nome fornecido
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));

        return fxmlLoader; // Retorna o carregador
    }

    public static void main(String[] args) {
        // Método principal que inicia a aplicação JavaFX
        launch(); // Chama o método launch(), que inicializa a interface gráfica
    }
}
