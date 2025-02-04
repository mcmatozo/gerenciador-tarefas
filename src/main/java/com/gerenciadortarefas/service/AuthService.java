package com.gerenciadortarefas.service;

import com.gerenciadortarefas.model.User;
import com.gerenciadortarefas.repositories.UserRepository;

public class AuthService {
    
    // Instância do repositório de usuários, responsável por interagir com a camada de persistência
    private UserRepository repository;

    // Construtor que inicializa o repositório
    public AuthService(){
        this.repository = new UserRepository();
    }

    /**
     * Método responsável por registrar um novo usuário no sistema.
     * Ele cria um objeto User e o salva no banco de dados através do repositório.
     * 
     * @param userName Nome do usuário
     * @param userEmail Email do usuário
     * @param userPassword Senha do usuário
     */
    public void register(String userName, String userEmail, String userPassword){
        // Cria um novo usuário com os dados fornecidos
        User user = new User(userName, userEmail, userPassword);
        // Salva o usuário no banco de dados usando o repositório
        repository.save(user);        
    }

    /**
     * Método responsável por autenticar um usuário no sistema.
     * Ele busca um usuário pelo email e verifica se a senha fornecida está correta.
     * 
     * @param userEmail Email do usuário
     * @param userPassword Senha do usuário
     * @return Retorna o usuário autenticado, caso os dados estejam corretos
     * @throws RuntimeException Se o email ou a senha forem incorretos
     */
    public User login(String userEmail, String userPassword){
        // Busca o usuário pelo email no banco de dados
        User user = repository.findByEmail(userEmail);

        // Verifica se o usuário existe e se a senha está correta
        if(user == null || !user.getPassword().equals(userPassword)){
            System.out.println("Email ou senha incorretos!");
            throw new RuntimeException("Email ou senha incorretos!");
        }

        // Retorna o usuário autenticado
        return user;
    }
}
