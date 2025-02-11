package com.gerenciadortarefas.service;

import java.util.List;

import com.gerenciadortarefas.model.Locale;
import com.gerenciadortarefas.model.UserSession;
import com.gerenciadortarefas.repositories.LocaleRepository;

public class LocaleService {

    private LocaleRepository localeRepository;

    public LocaleService() {
        localeRepository = new LocaleRepository();
    }

    public void create(String name, String location) 
    {
        int userId = UserSession.getInstance().getUser().getId();
        Locale locale = new Locale(userId, name, location);
        localeRepository.create(locale);
    }

    public List<Locale> list() {
        return localeRepository.list();
    }
}
