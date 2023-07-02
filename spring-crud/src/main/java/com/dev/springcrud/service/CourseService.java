package com.dev.springcrud.service;

import com.dev.springcrud.exception.RecordNotFoundException;
import com.dev.springcrud.model.Course;
import com.dev.springcrud.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

//    public CourseService(CourseRepository courseRepository) { Another approach instead of using @Autowired
//        this.courseRepository = courseRepository;
//    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id)
                .map(record -> {
                    record.setName(course.getName());
                    record.setCategory(course.getCategory());
                    return courseRepository.save(record);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
//        courseRepository.findById(id) // Another option to do the same thing
//                .map(record -> {
//                    courseRepository.deleteById(id);
//                    return true;
//                })
//                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}
