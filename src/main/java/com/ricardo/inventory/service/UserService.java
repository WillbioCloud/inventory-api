package com.ricardo.inventory.service;

import com.ricardo.inventory.entity.User;
import com.ricardo.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User getProfile(String login) {
        return (User) repository.findByLogin(login);
    }

    public User updateProfile(String login, String newName, String newPassword) {
        User user = (User) repository.findByLogin(login);
        if (user == null) throw new RuntimeException("Usuário não encontrado");

        // Atualiza o nome se foi enviado
        if (newName != null && !newName.isBlank()) {
            user.setName(newName);
        }

        // Atualiza a senha se foi enviada (com criptografia!)
        if (newPassword != null && !newPassword.isBlank()) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        }

        return repository.save(user);
    }
}