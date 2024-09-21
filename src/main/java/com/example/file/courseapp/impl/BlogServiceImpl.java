package com.example.file.courseapp.impl;

import com.example.file.courseapp.dto.BlogDto;
import com.example.file.courseapp.entity.Blog;
import com.example.file.courseapp.exception.NotFoundException;
import com.example.file.courseapp.repository.BlogRepository;
import com.example.file.courseapp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public ResponseEntity<?> create(BlogDto dto) {
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setPublishDate(LocalDateTime.now());
        blogRepository.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);

    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Blog blog = this.blogRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Blog not found"));

        return ResponseEntity.status(HttpStatus.OK).body(blog);

    }

    @Override
    public ResponseEntity<?> update(BlogDto blog, Long id) {
        Blog bl = this.blogRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Blog not found"));

        bl.setTitle(blog.getTitle());
        bl.setContent(blog.getContent());
        bl.setUpdatedAt(LocalDateTime.now());
        blogRepository.save(bl);
        return ResponseEntity.status(HttpStatus.OK).body("blog update successfully");

    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Blog bl = this.blogRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Blog not found"));
        blogRepository.delete(bl);
        return ResponseEntity.status(HttpStatus.OK).body("blog delete successfully");
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Blog> all = blogRepository.findAll();
        if (!all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(all);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" blogs are empty");
    }

    @Override
    public ResponseEntity<?> getAllPage(Pageable pageable) {
        List<Blog> blogs = this.blogRepository.findAll();
        if (!blogs.isEmpty()) {

            List<BlogDto> blogDtos = blogs.stream()
                    .map(blog -> new BlogDto(
                            blog.getId(),
                            blog.getTitle(),
                            blog.getContent(),
                            blog.getPublishDate(),
                            blog.getUpdatedAt()
                    )).collect(Collectors.toList());

            int start = pageable.getPageNumber() * pageable.getPageSize();
            int end = Math.min(start + pageable.getPageSize(), blogDtos.size());

            List<BlogDto> output = blogDtos.subList(start, end);

            return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(output, pageable, blogDtos.size()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" blog is empty");
    }

}
