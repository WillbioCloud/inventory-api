package com.ricardo.inventory.controller;

import com.ricardo.inventory.dto.AuthenticationDTO;
import com.ricardo.inventory.dto.RegisterDTO;
import com.ricardo.inventory.entity.User;
import com.ricardo.inventory.repository.UserRepository;
import com.ricardo.inventory.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        // 1. Tenta autenticar o usuário usando os dados do banco
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // 2. Se a senha bater, gera o Token JWT
        var token = tokenService.generateToken((User) auth.getPrincipal());

        // 3. Devolve o token para o Postman/Front-end (como JSON, para facilitar)
        return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        // 1. Verifica se o e-mail já existe
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        // 2. Encripta a senha com BCrypt (nunca salva em texto puro!)
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(null, data.login(), encryptedPassword);

        // 3. Salva no banco
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}