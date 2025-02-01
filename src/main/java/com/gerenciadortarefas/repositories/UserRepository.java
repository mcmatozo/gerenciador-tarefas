package com.gerenciadortarefas.repositories;

import java.sql.SQLException;

import com.gerenciadortarefas.daos.UserDAO;
import com.gerenciadortarefas.model.User;

public class UserRepository {

    private UserDAO userDAO;

    public UserRepository() {
        
        try {
            this.userDAO = new UserDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(User user) {

        try {
            userDAO.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
    
}
