package com.gerenciadortarefas.repositories;

import java.sql.SQLException;
import java.util.List;

import com.gerenciadortarefas.daos.LocaleDAO;
import com.gerenciadortarefas.model.Locale;

public class LocaleRepository {


    private LocaleDAO localeDAO;
    
    public LocaleRepository() {
        try {
            this.localeDAO = new LocaleDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Locale locale) {
        localeDAO.create(locale);
    }

    public void delete(int localeId, int userId) {
        localeDAO.delete(localeId, userId);
    }

    public List<Locale> list(int userId) {
        return localeDAO.list(userId);
    }

    public void update(int userId, int localeId, String name, String address) {
        localeDAO.update(userId, localeId, name, address);
    }
}
