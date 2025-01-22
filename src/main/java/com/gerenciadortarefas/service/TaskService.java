package com.gerenciadortarefas.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gerenciadortarefas.adapter.LocalDateAdapter;
import com.gerenciadortarefas.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskService {
    // classe pra metodos que voce vai reutilizar
    private ObservableList<Task> tasks = FXCollections.observableArrayList();// inicializou lista do tipo tarefa e vazia

    private static final String FILE_PATH = (System.getProperty("user.dir")
            + "/src/main/java/com/gerenciadortarefas/data/tasks.json");

    public TaskService() {
        loadTask();// o construtor tem o mesmo nome da classe
    }

    public void addTask(Task task) {
        // metodo
        tasks.add(task);
        saveTask();

    }

    public ObservableList<Task> initObservable() {
        return tasks;
    }

    public ObservableList<Task> getTasks() {
        // loadTask();
        return tasks;
    }

    public void saveTask() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();// criou a biblioteca que vamos utilizar, e explicou como quer que trate e
                          // adapte o jeito que transforma cada informcao

        List<Task> allTasks = new ArrayList<>();

        try (var reader = new FileReader(FILE_PATH)) {// filereader buscou o arquivo
            Type listType = new TypeToken<List<Task>>() {
            }.getType();
            List<Task> existingTasks = gson.fromJson(reader, listType);
            if (existingTasks != null && tasks.isEmpty()) {
                allTasks.addAll(existingTasks);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

        allTasks.addAll(tasks);

        try (var writer = new FileWriter(FILE_PATH)) { // o filewriter pega o arquivo e permita que escreva, diferente
                                                       // do reader (só ler)
            gson.toJson(allTasks, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadTask() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Registra o TypeAdapter
                .create();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Task>>() {
            }.getType();
            List<Task> jsonTasks = gson.fromJson(reader, listType);

            if (jsonTasks != null) {
                tasks.addAll(jsonTasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
