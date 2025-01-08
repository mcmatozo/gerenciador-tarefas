module com.gerenciadortarefas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gerenciadortarefas to javafx.fxml;
    opens com.gerenciadortarefas.controller to javafx.fxml;
    opens com.gerenciadortarefas.model to javafx.base;

    exports com.gerenciadortarefas;
}
