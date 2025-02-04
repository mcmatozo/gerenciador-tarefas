package com.gerenciadortarefas; // Define o pacote onde a classe está localizada

import com.gerenciadortarefas.database.DatabaseSetup; // Importa a classe responsável por criar as tabelas no banco de dados

public class Launcher { // Declara a classe Launcher, que será usada para iniciar a aplicação

    public static void main(String[] args) { // Método principal que inicia a execução do programa
        
        // Chama o método para criar as tabelas do banco de dados caso elas ainda não existam
        DatabaseSetup.createTables();
        
        // Inicia a aplicação JavaFX chamando o método main da classe App
        App.main(args);
    }                       
}
