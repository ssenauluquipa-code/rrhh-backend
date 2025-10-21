package com.rrhh.sistema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rrhh.sistema.model.User;
import com.rrhh.sistema.security.JwtUtil;

import jakarta.annotation.PostConstruct;

@Service
public class AuthService {
	private final List<User> users = new ArrayList<>();
	private final JwtUtil jwtUtil;
	
	public AuthService(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@PostConstruct
	public void init() {
		// Usuarios simulados en memoria
		users.add(new User(1L, "admin", "admin@empresa.com", "123456", "ADMIN"));
        users.add(new User(2L, "rrhh", "hr@empresa.com", "12345", "HR"));
	}

	
    public String login(String email, String password){
        return users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .map( u -> jwtUtil.generateToken(u.getEmail(), u.getRole()))
                .orElse(null);
    }

    public User getUserByEmail(String email){
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

}
