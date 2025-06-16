package com.example.provaeliel.models.DTOs;

import com.example.provaeliel.models.enums.RoleUser;

public record RegisterDto(String login, String email, String password, RoleUser role) {
    
}
