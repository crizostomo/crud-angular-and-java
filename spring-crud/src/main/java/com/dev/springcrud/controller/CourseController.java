package com.dev.springcrud.controller;

import com.dev.springcrud.dto.CourseDTO;
import com.dev.springcrud.dto.CoursePageDTO;
import com.dev.springcrud.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> list() {
        return courseService.list();
    }

    @GetMapping
    public CoursePageDTO findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int pageSize) {
        return courseService.findAll(page, pageSize);
    }

    @GetMapping("/searchByName")
    public List<CourseDTO> findByName(@RequestParam @NotNull @NotBlank String name) {
        return courseService.findByName(name);
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid CourseDTO courseDTO) {
        return courseService.create(courseDTO);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDTO courseDTO) {
        return courseService.update(id, courseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
    }
}