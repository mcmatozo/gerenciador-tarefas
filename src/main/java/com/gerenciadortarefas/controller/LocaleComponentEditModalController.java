package com.gerenciadortarefas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Locale;
import com.gerenciadortarefas.service.LocaleService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LocaleComponentEditModalController  implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField name;

    @FXML
    private Button saveButton;

    private LocaleListController listController;

    private Locale locale;

    private LocaleService service;

    public LocaleComponentEditModalController() {
        service = new LocaleService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void editLocale(ActionEvent event) {
        service.edit(locale.getId(), name.getText(), address.getText());

        listController.loadLocales();

        // Fecha o modal após salvar a tarefa
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close(); // Fecha a janela do modal
    }

    public void setListControler(LocaleListController localeListController) {
        listController = localeListController;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;

        address.setText(locale.getLocation());
        name.setText(locale.getName());
    }
}
