package com.dev.springcrud.service;

import com.dev.springcrud.dto.CourseDTO;
import com.dev.springcrud.dto.mapper.CourseMapper;
import com.dev.springcrud.enums.Category;
import com.dev.springcrud.exception.RecordNotFoundException;
import com.dev.springcrud.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

//    public CourseService(CourseRepository courseRepository) { Another approach instead of using @Autowired
//        this.courseRepository = courseRepository;
//    }

    public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());

//        List<Course> courses = courseRepository.findAll();
//        List<CourseDTO> dtos = new ArrayList<>(courses.size());
//        for (Course course : courses) {
//            CourseDTO dto = new CourseDTO(course.getId(), course.getName(), course.getCategory());
//            dtos.add(dto);
//        }
//        return dtos;
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO courseDTO) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(record -> {
                    record.setName(courseDTO.name());

                    Category category = getCategoryByValue(courseDTO.category());
                    record.setCategory(category);

//                    record.setCategory(Category.valueOf(courseDTO.category()));
                    return courseRepository.save(record);
                }).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    private Category getCategoryByValue(String categoryValue) {
        for (Category category : Category.values()) {
            if (category.getValue().equalsIgnoreCase(categoryValue)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + categoryValue);
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
