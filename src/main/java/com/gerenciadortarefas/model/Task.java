package com.gerenciadortarefas.model;

import java.time.LocalDate;

public class Task {

    // Declaração dos atributos da classe Task
    private String name;          // Nome da tarefa
    private String description;   // Descrição da tarefa
    private LocalDate executedAt; // Data de execução da tarefa
    private LocalDate finishedAt; // Data de finalização da tarefa
    private LocalDate createdAt;  // Data de criação da tarefa
    private LocalDate updatedAt;  // Data da última atualização da tarefa
    private boolean completed;    // Status de conclusão da tarefa (completa ou não)
    private int id;  
    private int localeId;
    private Locale locale;
                 // Identificador único da tarefa (ID)

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    // Construtor para criação de tarefa com todos os parâmetros (incluindo 'id' e 'completed')
    public Task(int id, Locale locale, String name, String description, LocalDate executedAt,
            LocalDate finishedAt, boolean completed) {
        this.id = id;               // Atribui o ID da tarefa
        this.name = name;           // Atribui o nome da tarefa
        this.description = description; // Atribui a descrição da tarefa
        this.executedAt = executedAt;   // Atribui a data de execução
        this.finishedAt = finishedAt;   // Atribui a data de finalização
        this.completed = completed;     // Atribui o status de completada
        this.locale = locale;
    }

    // Construtor para criação de tarefa sem o 'id' (usado na criação de novas tarefas)
    public Task(String name, String description, LocalDate executedAt,
            LocalDate finishedAt, Locale locale) {
        this(0, locale, name, description, executedAt, finishedAt, false); // Chama o outro construtor com 'id' 0 e 'completed' como falso
    }

    // Métodos de acesso (getters) e modificação (setters) para cada atributo da classe

    // Retorna o status de completada da tarefa
    public boolean isCompleted() {
        return completed;
    }

    // Define o status de completada da tarefa
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Retorna o nome da tarefa
    public String getName() {
        return name;
    }

    // Define o nome da tarefa
    public void setName(String name) {
        this.name = name;
    }

    // Retorna a descrição da tarefa
    public String getDescription() {
        return description;
    }

    // Define a descrição da tarefa
    public void setDescription(String description) {
        this.description = description;
    }

    // Retorna a data de execução da tarefa
    public LocalDate getExecutedAt() {
        return executedAt;
    }

    // Define a data de execução da tarefa
    public void setExecutedAt(LocalDate executedAt) {
        this.executedAt = executedAt;
    }

    // Retorna a data de finalização da tarefa
    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    // Define a data de finalização da tarefa
    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }

    // Retorna a data da última atualização da tarefa
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    // Retorna a data de criação da tarefa
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // Retorna o ID da tarefa
    public int getId() {
        return id;
    }

    public int getLocaleId() {
        return localeId;
    }

    public Locale getLocale() {
        return locale;
    }
    
}
