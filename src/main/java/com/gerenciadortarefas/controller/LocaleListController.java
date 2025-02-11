package com.gerenciadortarefas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class LocaleListController {

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private Button createLocaleButton;

    @FXML
    private TableColumn<?, ?> editColumn;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableColumn<?, ?> nome;

    @FXML
    private TableColumn<?, ?> removeColumn;

    @FXML
    private StackPane rootPane;

    @FXML
    private TableView<?> tabela;

    @FXML
    public void handleOpenLocaleModal(ActionEvent event) {

    }

}
