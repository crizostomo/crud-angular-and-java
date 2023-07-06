package com.dev.springcrud.dto.mapper;

import com.dev.springcrud.dto.CourseDTO;
import com.dev.springcrud.dto.LessonDTO;
import com.dev.springcrud.enums.Category;
import com.dev.springcrud.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getShareableYoutubeUrl()))
                .collect(Collectors.toList());
        
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());

        Category category = getCategoryByValue(courseDTO.category());
        course.setCategory(category);

        course.setStatus("Active");
        return course;
    }

    private Category getCategoryByValue(String categoryValue) {
        for (Category category : Category.values()) {
            if (category.getValue().equalsIgnoreCase(categoryValue)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + categoryValue);
    }
}
