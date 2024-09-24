package com.example.file.courseapp.controller;

import com.example.file.courseapp.dto.BlogDto;
import com.example.file.courseapp.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @Operation(summary = "to blog the course", description = "blog successfully created",
            tags = "CREATE"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "blog successfully created"),
            @ApiResponse(responseCode = "404", description = " blog not found")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BlogDto blog) {
        ResponseEntity response = this.blogService.create(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "to get the blog", description = "blog get by id", tags = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "blog get successfully by id"),
            @ApiResponse(responseCode = "404", description = " blog not found")
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ResponseEntity response = this.blogService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to update the blog", description = "blog update by id", tags = "UPDATE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "blog update successfully by id"),
            @ApiResponse(responseCode = "404", description = " blog not found")
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody BlogDto blog, @PathVariable Long id) {
        ResponseEntity response = this.blogService.update(blog, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to delete the blog", description = "blog delete by id", tags = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "blog delete successfully by id"),
            @ApiResponse(responseCode = "404", description = " blog not found")
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseEntity response = this.blogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to get all the blog", description = "blog get all", tags = "GET-ALL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "blog get all successfully "),
            @ApiResponse(responseCode = "404", description = " blog not found")
    })
    @GetMapping("/get_all")
    public ResponseEntity<?> getAll() {
        ResponseEntity response = this.blogService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to get all page the blog", description = "blog get all page", tags = "GET-ALL-PAGE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "blog get all page successfully "),
            @ApiResponse(responseCode = "404", description = " blog not found")
    })
    @GetMapping("/get_all_page")
    public ResponseEntity<?> getAllPage(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        ResponseEntity<?> pag = blogService.getAllPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pag);
    }
}
