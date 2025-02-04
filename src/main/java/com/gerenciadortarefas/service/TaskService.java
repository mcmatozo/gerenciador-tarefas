package com.gerenciadortarefas.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gerenciadortarefas.adapter.LocalDateAdapter;
import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.repositories.TaskRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskService {
    
    // Lista observável para armazenar tarefas e atualizar automaticamente a interface gráfica (JavaFX)
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    // Repositório responsável por interagir com a camada de persistência (banco de dados)
    private TaskRepository repository;
    
    // Caminho do arquivo JSON onde as tarefas serão salvas localmente
    private static final String FILE_PATH = (System.getProperty("user.dir")
            + "/src/main/java/com/gerenciadortarefas/data/tasks.json");

    /**
     * Construtor da classe TaskService.
     * Inicializa o repositório de tarefas e carrega as tarefas salvas.
     */
    public TaskService() {
        this.repository = new TaskRepository();
        loadTask(); // Carrega as tarefas do banco de dados ao iniciar
    }

    /**
     * Adiciona uma nova tarefa à lista e a salva no banco de dados.
     * @param task Objeto Task a ser adicionado
     */
    public void addTask(Task task) {
        tasks.add(task); // Adiciona a tarefa à lista observável
        repository.save(task); // Salva a tarefa no banco de dados
    }

    /**
     * Retorna a lista observável de tarefas.
     * @return ObservableList<Task> Lista de tarefas
     */
    public ObservableList<Task> initObservable() {
        return tasks;
    }

    /**
     * Retorna a lista de tarefas sem carregar novamente do banco de dados.
     * @return ObservableList<Task> Lista de tarefas
     */
    public ObservableList<Task> getTasks() {
        return tasks;
    }

    /**
     * Salva todas as tarefas em um arquivo JSON local.
     * Usa a biblioteca Gson para converter as tarefas para JSON.
     */
    public void saveTask() {
        // Configura o Gson com um adaptador para converter LocalDate corretamente
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        List<Task> allTasks = new ArrayList<>(); // Lista para armazenar todas as tarefas

        // Tenta ler as tarefas existentes do arquivo JSON
        try (var reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Task>>() {}.getType();
            List<Task> existingTasks = gson.fromJson(reader, listType);
            
            // Se existirem tarefas no arquivo e a lista de tarefas atual estiver vazia, adiciona as tarefas lidas
            if (existingTasks != null && tasks.isEmpty()) {
                allTasks.addAll(existingTasks);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Exibe erros de leitura do arquivo
        }

        // Adiciona as tarefas atuais à lista antes de salvar
        allTasks.addAll(tasks);

        // Tenta escrever a lista de tarefas no arquivo JSON
        try (var writer = new FileWriter(FILE_PATH)) {
            gson.toJson(allTasks, writer); // Converte a lista de tarefas para JSON e escreve no arquivo
        } catch (Exception e) {
            e.printStackTrace(); // Exibe erros de escrita no arquivo
        }
    }

    /**
     * Carrega as tarefas do banco de dados e adiciona à lista observável.
     */
    private void loadTask() {
       tasks.addAll(repository.list()); // Busca todas as tarefas no banco de dados e adiciona à lista
    }
}
