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

    public Locale create(String name, String location) 
    {
        int userId = UserSession.getInstance().getUser().getId();
        Locale locale = new Locale(userId, name, location);
        localeRepository.create(locale);

        return locale;
    }

    public List<Locale> list() {
        int userId = UserSession.getInstance().getUser().getId();

        return localeRepository.list(userId);
    }

    public void delete(int localeId) 
    {
        int userId = UserSession.getInstance().getUser().getId();
        localeRepository.delete(localeId, userId);
    }

    public void edit(int localeId, String name, String address) 
    {
        int userId = UserSession.getInstance().getUser().getId();
        localeRepository.update(userId, localeId, name, address);
    }
}
