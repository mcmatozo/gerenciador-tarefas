package com.gerenciadortarefas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Carregar a tela de login
        scene = new Scene(loadFXML("view/home"), 300, 200); // Ajuste o tamanho conforme necessário
        stage.setScene(scene);
        stage.setTitle("Tela de Login");
        stage.show();
    }

    // Método para carregar diferentes FXMLs
    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    // Método para mudar para a tela principal
    public void switchToHome() throws IOException {
        scene.setRoot(loadFXML("view/home")); // Carregar a tela principal
    }
}