module com.gerenciadortarefas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gerenciadortarefas to javafx.fxml;
    exports com.gerenciadortarefas;
}
