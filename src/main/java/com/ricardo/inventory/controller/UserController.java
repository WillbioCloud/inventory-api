package com.ricardo.inventory.controller;

import com.ricardo.inventory.entity.User;
import com.ricardo.inventory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    // Rota GET para buscar os dados do usuário logado
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getMe() {
        // Extrai o email do token ativo
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = service.getProfile(login);

        // Devolvemos um Map para evitar criar um DTO só para isso e proteger a senha
        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "name", user.getName() != null ? user.getName() : "Administrador",
                "email", user.getLogin()
        ));
    }

    // Rota PUT para atualizar o próprio perfil (Nome e Senha)
    @PutMapping("/me")
    public ResponseEntity<Map<String, Object>> updateMe(@RequestBody Map<String, String> body) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User updatedUser = service.updateProfile(login, body.get("name"), body.get("password"));

        return ResponseEntity.ok(Map.of(
                "id", updatedUser.getId(),
                "name", updatedUser.getName(),
                "email", updatedUser.getLogin()
        ));
    }
}