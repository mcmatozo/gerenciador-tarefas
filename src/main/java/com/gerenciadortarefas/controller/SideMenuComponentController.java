package com.gerenciadortarefas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SideMenuComponentController {

    @FXML
    private VBox sideMenu;

    @FXML
    private Button menuButton;

    private boolean isExpanded = false;

    @FXML
    public void initialize() {
    }

    @FXML
    public void toggleMenu() {
        if (isExpanded) {
            sideMenu.setPrefWidth(50);
        } else {
            sideMenu.setPrefWidth(250);
        }
        isExpanded = !isExpanded;
    }
}
