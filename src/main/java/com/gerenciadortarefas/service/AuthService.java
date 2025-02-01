package com.gerenciadortarefas.service;

import com.gerenciadortarefas.model.User;
import com.gerenciadortarefas.repositories.UserRepository;

public class AuthService {
    
    private UserRepository repository;

    public AuthService(){
        this.repository = new UserRepository();
    }

    public void register(String userName, String userEmail, String userPassword){
        User user = new User(userName, userEmail, userPassword);
        repository.save(user);        
    }

    public User login(String userEmail, String userPassword){
        User user = repository.findByEmail(userEmail);

        if(user == null && !user.getPassword().equals(userPassword)){
            System.out.println("Email ou senha incorretos!");
            throw new RuntimeException("Email ou senha incorretos!");
        }

        return user;
    }
}
