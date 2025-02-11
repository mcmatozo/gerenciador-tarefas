package com.gerenciadortarefas.repositories;

import java.lang.reflect.Array;
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

    public List<Locale> list() {
        return localeDAO.list();
    }
}
