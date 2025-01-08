package com.gerenciadortarefas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class TaskComponentController implements Initializable {
    @FXML // ta apontando pra view
    private Text title;

    @FXML
    private Text description;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    public void setTaskData(String title, String description) {
        this.title.setText(title);

        if (description.isEmpty()) {
            this.description.setVisible(false);
        } else {
            this.description.setText(description);
        }
    }
}
