package com.gerenciadortarefas.service;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.gerenciadortarefas.model.Task;
//import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskService {
    // classe pra metodos que voce vai reutilizar
    private ObservableList<Task> tasks = FXCollections.observableArrayList();// inicializou lista do tipo tarefa e vazia

    private static final String FILE_PATH = "tasks.json";

    public void addTask(Task task) {
        // metodo
        tasks.add(task);

    }

    private void saveTask() {
        // Gson gson = new Gson();

        // try (FileWriter writer = new FileWriter(FILE_PATH)) {
        // gson.toJson(tasks, writer);
        // } catch (Exception e) {
        // e.printStackTrace();
        // // TODO: handle exception
        // }
    }

    private void loadTask() {
        // Type listType = new TypeToken<List<Task>>() {
        // }.getType();
    }

    public List<Task> getTask() {
        return tasks;
    }

}
