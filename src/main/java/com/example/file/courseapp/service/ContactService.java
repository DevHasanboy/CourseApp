package com.example.file.courseapp.service;

import com.example.file.courseapp.dto.ContactDto;
import org.springframework.http.ResponseEntity;

public interface ContactService {
    ResponseEntity<?> getAllContacts();

    ResponseEntity<?> create(ContactDto dto);

}
