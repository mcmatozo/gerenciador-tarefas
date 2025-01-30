module com.gerenciadortarefas {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.graphics;
    requires java.sql;


    opens com.gerenciadortarefas to javafx.fxml;
    opens com.gerenciadortarefas.controller to javafx.fxml;
    opens com.gerenciadortarefas.model to com.google.gson, javafx.base;

    exports com.gerenciadortarefas;
}
