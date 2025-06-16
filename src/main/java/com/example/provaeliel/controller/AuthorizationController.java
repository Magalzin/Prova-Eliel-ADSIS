package com.example.provaeliel.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.provaeliel.infra.security.TokenSerivce;
import com.example.provaeliel.models.DTOs.AuthenticationDto;
import com.example.provaeliel.models.DTOs.LoginResponseDto;
import com.example.provaeliel.models.DTOs.RegisterDto;
import com.example.provaeliel.models.UserModel;
import com.example.provaeliel.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenSerivce tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto data) {
        var userPassord = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = this.authenticationManager.authenticate(userPassord);

        var token = tokenService.generateToken((UserModel) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDto data) {
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Erro ao registrar");
        }
        
        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel user = new UserModel(data.login(), data.email(), encriptedPassword, data.role());
        
        this.repository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/id")
        .buildAndExpand(user.getId())
        .toUri();
        
        return ResponseEntity.created(uri).build();

    }
}
