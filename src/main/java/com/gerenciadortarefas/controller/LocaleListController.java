package com.gerenciadortarefas.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gerenciadortarefas.model.Locale;
import com.gerenciadortarefas.service.LocaleService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LocaleListController implements Initializable  {

    @FXML
    private Button createLocaleButton;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableColumn<Locale, String> address;

    @FXML
    private TableColumn<Locale, String> nome;

    @FXML
    private TableColumn<Locale, Void> removeColumn;

    @FXML
    private TableColumn<Locale, Void> editColumn;

    @FXML
    private StackPane rootPane;

    @FXML
    private TableView<Locale> tabela;

    ObservableList<Locale> locales = FXCollections.observableArrayList();


    private LocaleService service;

    public LocaleListController() {
        service = new LocaleService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarBotoesDeAcao();
        loadLocales();
    }

    @FXML
    public void handleOpenLocaleModal(ActionEvent event) {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/com/gerenciadortarefas/view/localeComponentModal.fxml"));
            Parent root = fxmlLoader.load();

            LocaleComponentModalController controller = fxmlLoader.getController();
            controller.setListControler(this);

            Stage stage = new Stage();
            stage.setTitle("Criar Novo Local");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarBotoesDeAcao() {
        // adiciona a funcionalidade de edição na tabela
        // lambda é algo que ajuda a implementar interfaces, algo que diz "faz isso"
        // <> cria nova celula na tabela, para cada célula na coluna, crie uma nova
        // célula
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Editar");
            {
                editButton.setOnAction(event -> {
                    Locale locale = getTableView().getItems().get(getIndex());
                    editarLocale(locale);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        // adiciona a funcionalidade de remoção na tabela
        removeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Excluir");

            {
                // define uma ação
                // encontra aluno específico
                // exclui o aluno
                deleteButton.setOnAction(event -> {
                    Locale locale = getTableView().getItems().get(getIndex());
                    excluirLocale(locale, getIndex());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
    }

    private void editarLocale(Locale locale) {
        try {
            // carrega a tela para editar um aluno existente
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gerenciadortarefas/view/localeComponentEditModal.fxml"));
            Parent root = fxmlLoader.load();
            
            LocaleComponentEditModalController controller = fxmlLoader.getController();
            controller.setLocale(locale);
            controller.setListControler(this);

            Stage stage = new Stage();
            stage.setTitle("Editar Local");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace(); // imprime a stack trace em caso de erro
        }
    }

    private void excluirLocale(Locale locale, int index) {
        service.delete(locale.getId());
        locales.remove(locale);
    }

    public void addLocaleInTable(Locale locale) {
        locales.add(locale);
    }

    public void loadLocales() {
        locales.clear();

        List<Locale> localeList = service.list();
        locales.addAll(localeList);

        nome.setCellValueFactory(new PropertyValueFactory<Locale, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Locale, String>("location"));

        tabela.setItems(locales);
    }
}
