package com.example.file.courseapp.controller;

import com.example.file.courseapp.dto.TeacherDto;
import com.example.file.courseapp.service.TeacherService;
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
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @Operation(summary = "to create the teacher", description = "teacher successfully created",
            tags = "CREATE"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "teacher successfully created"),
            @ApiResponse(responseCode = "404", description = " teacher not found")
    })
    @PostMapping("/create/")
    public ResponseEntity<?> create(@RequestBody TeacherDto dto){
        ResponseEntity response=this.teacherService.createTeacher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "to get the teacher", description = "teacher get by id", tags = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "teacher get successfully by id"),
            @ApiResponse(responseCode = "404", description = " teacher not found")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        ResponseEntity response=this.teacherService.findTeacherById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to update the teacher", description = "teacher update by id", tags = "UPDATE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "teacher update successfully by id"),
            @ApiResponse(responseCode = "404", description = " teacher not found")
    })

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody TeacherDto dto,@PathVariable Long id){
        ResponseEntity response=this.teacherService.updateTeacher(dto,id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to delete the teacher", description = "teacher delete by id", tags = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "teacher delete successfully by id"),
            @ApiResponse(responseCode = "404", description = " teacher not found")
    })
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id){
        ResponseEntity response=this.teacherService.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to get all the teacher", description = "teacher get all", tags = "GET-ALL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "teacher get all successfully "),
            @ApiResponse(responseCode = "404", description = " teacher not found")
    })
    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        ResponseEntity response=this.teacherService.findAllTeachers();
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
        ResponseEntity<?> pag=this.teacherService.getAllPage( pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pag);

    }
}
