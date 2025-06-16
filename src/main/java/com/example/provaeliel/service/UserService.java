package com.example.provaeliel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.provaeliel.models.DTOs.UserUpdateDto;
import com.example.provaeliel.models.UserModel;
import com.example.provaeliel.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserModel> findAllUsers() {
        return repository.findAll();
    }

    public UserModel updateMyUser(String login, UserUpdateDto updated) {

        UserModel user = (UserModel) repository.findByLogin(login);


        String encriptedPassword = new BCryptPasswordEncoder().encode(updated.password());
        user.setEmail(updated.email());
        user.setLogin(updated.login());
        user.setPassword(encriptedPassword);

        return repository.save(user);
    }

    public UserModel updateUser(Long id, UserUpdateDto updated) {

        Optional<UserModel> userOpt = repository.findById(id);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Id não encontrado");
        }

        String encriptedPassword = new BCryptPasswordEncoder().encode(updated.password());
        UserModel user = userOpt.get();
        user.setEmail(updated.email());
        user.setLogin(updated.login());
        user.setPassword(encriptedPassword);
        return repository.save(user);

    }

    public UserModel deleteUser(Long id) {
        var optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        var user = optionalUser.get();
        repository.delete(user);
        return user;
    }
}
