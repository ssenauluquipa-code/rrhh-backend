package com.rrhh.sistema.service;

import com.rrhh.sistema.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init(){
        users.add(new User(1L, "admin", "admin@empresa.com", "123456", "ADMIN"));
        users.add(new User(2L, "hr", "hr@empresa.com", "123456", "HR"));
        users.add(new User(3L, "juan", "juan@empresa.com", "123456", "EMPLOYEE"));
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users);
    }

    public User createUser(User user){
        user.setId(idGenerator.getAndIncrement());
        users.add(user);
        return user;
    }
    public User updateUser(Long id, User update){
        User existing = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(existing != null){
            existing.setUsername(update.getUsername());
            existing.setEmail(update.getEmail());
            existing.setRole(update.getRole());
        }
        return  existing;
    }

    public User findByEmail(String email){
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
