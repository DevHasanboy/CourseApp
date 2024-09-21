package com.example.file.courseapp.controller;

import com.example.file.courseapp.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "to get all the contact", description = "contact get all", tags = "GET-ALL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "contact get all successfully "),
            @ApiResponse(responseCode = "404", description = " contact not found")
    })
    @GetMapping("/get_all/")
    public ResponseEntity<?> getAllContacts() {
        ResponseEntity response=this.contactService.getAllContacts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
