module com.gerenciadortarefas {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.base;

    // Declara as dependências do módulo, especificando quais bibliotecas externas são necessárias

    // requires javafx.controls; // Necessário para usar componentes gráficos como botões, labels e janelas no JavaFX
    // requires javafx.fxml; // Necessário para carregar arquivos FXML e manipular a interface gráfica
    // requires com.google.gson; // Necessário para converter objetos Java em JSON e vice-versa
    // requires javafx.graphics; // Necessário para renderizar gráficos e elementos visuais no JavaFX
    // requires java.sql; // Necessário para trabalhar com bancos de dados através do JDBC

    // Define quais pacotes do módulo serão acessíveis a outras partes do programa

    opens com.gerenciadortarefas to javafx.fxml; 
    // Permite que o JavaFX acesse as classes dentro do pacote "com.gerenciadortarefas"

    opens com.gerenciadortarefas.controller to javafx.fxml;
    // Permite que o JavaFX acesse as classes dentro do pacote "controller" para manipular eventos e lógica da UI

    opens com.gerenciadortarefas.model to com.google.gson, javafx.base;
    // Permite que o Gson acesse o pacote "model" para serialização (conversão para JSON)
    // O "javafx.base" é necessário para trabalhar com listas e propriedades observáveis no JavaFX

    exports com.gerenciadortarefas;
    // Permite que outros módulos utilizem as classes do pacote principal "com.gerenciadortarefas"
}
