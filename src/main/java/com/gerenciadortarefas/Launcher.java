package com.gerenciadortarefas;

import com.gerenciadortarefas.database.DatabaseSetup;

public class Launcher {
    public static void main(String[] args) {
        DatabaseSetup.createTables();
        App.main(args);
    }
}
