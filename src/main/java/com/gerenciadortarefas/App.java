package com.gerenciadortarefas;

import java.io.IOException;

import com.gerenciadortarefas.controller.AuthController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Carregar a tela de login
        FXMLLoader loader = loadFXML("view/auth");
        Parent root = loader.load();

        AuthController authController = loader.getController();
        
        authController.setStage(stage);
        scene = new Scene(root, 640, 480); // Ajuste o tamanho conforme necessário
        stage.setScene(scene);
        stage.setTitle("Tela de Login");
        stage.show();
    }

    // Método para carregar diferentes FXMLs
    private FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
}