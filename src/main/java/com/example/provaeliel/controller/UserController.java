package com.example.provaeliel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.provaeliel.models.DTOs.UserUpdateDto;
import com.example.provaeliel.models.UserModel;
import com.example.provaeliel.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    // ROLE: USER
    @GetMapping("/self")
    public ResponseEntity<UserDetails> getUser(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(user);
    }

    @PutMapping("/self/update")
    public ResponseEntity<UserModel> updateMyself(@AuthenticationPrincipal UserDetails user,
            @RequestBody UserUpdateDto model) {
        try {
            return ResponseEntity.ok(service.updateMyUser(user.getUsername(), model));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ROLE: ADMIN

    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getAll() {
        try {
            return ResponseEntity.ok(service.findAllUsers());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto model) {
        try {
            return ResponseEntity.ok(service.updateUser(id, model));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
