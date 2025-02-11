package com.gerenciadortarefas.model;

public class Locale {
    
    private String name;
    private String location;
    private int id;
    private int userId;

    public Locale(int id, int userId, String name, String location) {
        this.name = name;
        this.location = location;
        this.id = id;
        this.userId = userId;
    }

    public Locale(int userId, String name, String location) {
        this(0, userId, name, location);//novo construtor que nao recebe o id mas ja recebe o 0, e chama o metodo locale de cima
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    //reescreve o metodo (como se fizesse um desenho e outra pessoa apagasse ele e desenhasse por cima)
    @Override 
    public String toString(){
        return name;//retorna o nome do local
    }
}
