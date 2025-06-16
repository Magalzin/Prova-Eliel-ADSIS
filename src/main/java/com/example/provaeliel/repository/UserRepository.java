package com.example.provaeliel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.provaeliel.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
    UserDetails findByLogin(String login);
}
