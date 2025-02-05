package com.gerenciadortarefas.singleton;

import javafx.stage.Stage;

public class StageSingleton {

    private static StageSingleton instance;
    private Stage stage;

    private StageSingleton() {}

    public static StageSingleton getInstance() {
        if (instance == null) {
            instance = new StageSingleton();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void clearStage() {
        stage = null;
    }
}
