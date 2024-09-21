package com.example.file.courseapp.impl;

import com.example.file.courseapp.dto.ContactDto;
import com.example.file.courseapp.entity.Contact;
import com.example.file.courseapp.entity.Course;
import com.example.file.courseapp.repository.ContactRepository;
import com.example.file.courseapp.repository.CourseRepository;
import com.example.file.courseapp.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final CourseRepository courseRepository;

    @Override
    public ResponseEntity<?> getAllContacts() {
        List<Course> all = this.courseRepository.findAll();
        if (!all.isEmpty()) {
            ContactDto contactDto = new ContactDto();
            contactDto.setCourseList(all);
            return ResponseEntity.status(HttpStatus.OK).body(contactDto);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("list is empty");
    }

    @Override
    public ResponseEntity<?> create(@RequestBody ContactDto dto) {
        List<Course> all = this.courseRepository.findAll();
        if (!all.isEmpty()) {
            Contact contact = new Contact();
            contact.setCourses(all);
            contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.OK).body("contact successfully created");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("list is empty");

    }

}
