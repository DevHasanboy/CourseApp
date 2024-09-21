package com.example.file.courseapp.controller;

import com.example.file.courseapp.dto.CourseDto;
import com.example.file.courseapp.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @Operation(summary = "to create the course", description = "course successfully created",
    tags = "CREATE"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course successfully created"),
            @ApiResponse(responseCode = "404", description = " course not found")
    })
    @PostMapping("/create/")
    public ResponseEntity<?> create(@RequestBody CourseDto dto) {
        ResponseEntity response = this.service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Operation(summary = "to get the course", description = "course get by id", tags = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course get successfully by id"),
            @ApiResponse(responseCode = "404", description = " course not found")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        ResponseEntity response = this.service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to update the course", description = "course update by id", tags = "UPDATE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course update successfully by id"),
            @ApiResponse(responseCode = "404", description = " course not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody CourseDto dto, @PathVariable Long id) {
        ResponseEntity response = this.service.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to delete the course", description = "course delete by id", tags = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course delete successfully by id"),
            @ApiResponse(responseCode = "404", description = " course not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseEntity response = this.service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to get all the course", description = "course get all", tags = "GET-ALL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course get all successfully "),
            @ApiResponse(responseCode = "404", description = " course not found")
    })
    @GetMapping("/get_all")
    public ResponseEntity<?> getAll() {
        ResponseEntity response = this.service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to get all page the course", description = "course get all page", tags = "GET-ALL-PAGE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course get all page successfully "),
            @ApiResponse(responseCode = "404", description = " course not found")
    })
    @GetMapping("/get_all_page")
    public ResponseEntity<?> getAllPage(@RequestParam(defaultValue = "0")  int page,
                                        @RequestParam(defaultValue = "10")  int size
                                        ){
        Pageable pageable= PageRequest.of(page, size);
        ResponseEntity<?> pag=this.service.getAllPage( pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pag);

    }

}
