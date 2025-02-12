package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.service.LocaleService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LocaleComponentModalController {

    @FXML
    private TextField address;

    @FXML
    private TextField name;

    @FXML
    private Button saveButton;

    private LocaleService service;

    private LocaleListController listController;

    @FXML
    public void createLocale(ActionEvent event) {
        String localeName = name.getText();
        String localeAddress = address.getText();
        service.create(localeName, localeAddress);

        if (listController != null) {
            listController.loadLocales();
        }


        // Fecha o modal após salvar a tarefa
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close(); // Fecha a janela do modal
    }

    public LocaleComponentModalController() {
        this.service = new LocaleService();
    }

    public void setListControler(LocaleListController localeListController) {
        listController = localeListController;
    }
}
