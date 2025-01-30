package com.gerenciadortarefas.model;

import java.time.LocalDate;

public class Task {
    private String name;
    private String description;
    private LocalDate executedAt;
    private LocalDate finishedAt;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean completed;

    public Task(String name, String description, LocalDate executedAt,
            LocalDate finishedAt, boolean completed) {

        this.name = name;
        this.description = description;
        this.executedAt = executedAt;
        this.finishedAt = finishedAt;
        this.completed = completed;
    }

    public Task(String name, String description, LocalDate executedAt,
            LocalDate finishedAt) {
        this(name, description, executedAt, finishedAt, false);// construtor
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDate executedAt) {
        this.executedAt = executedAt;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

}
