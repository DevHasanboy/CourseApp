package com.example.file.courseapp.auth;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<String> registerUser(String username, String password);

    ResponseEntity<?> getUserInfo(String username);

    ResponseEntity<?> getAll();

}