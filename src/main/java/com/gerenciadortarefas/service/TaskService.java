package com.gerenciadortarefas.service;

import com.gerenciadortarefas.model.Task;
import com.gerenciadortarefas.repositories.TaskRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


//um "gerente" para as tarefas no sistema. Ele é responsável por lidar com a criação, atualização e remoção de tarefas. 
//Ele interage com um repositório (um "banco de dados") onde as tarefas ficam armazenadas e também atualiza a interface 
//do usuário automaticamente quando uma tarefa é adicionada ou modificada.
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

    public void updateTask(Task task) {
        repository.update(task);
        updateTaskList(task);
    }

    private void loadTask() {
       tasks.addAll(repository.list());
    }

    public void setCompletedTask(Task task) {
        repository.setCompletedTask(task.getId(), task.isCompleted());
    }

    private void updateTaskList(Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).getId() == updatedTask.getId()) {
                tasks.set(i, updatedTask);
                break;
            }
        }
    }

    private void removeTaskList(Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).getId() == updatedTask.getId()) {
                tasks.remove(i);
                break;
            }
        }
    }

    public void removeTask(Task task) {
        repository.deleteTask(task.getId());
        removeTaskList(task);
    }
}
