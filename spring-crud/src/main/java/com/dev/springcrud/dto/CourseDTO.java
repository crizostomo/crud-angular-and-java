package com.dev.springcrud.dto;

import com.dev.springcrud.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @Length(min = 4, max = 100) String name,
        @NotNull @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String category,
        List<Lesson> lessons
//        @NotBlank @NotNull @Length(max = 10)  @Pattern(regexp = "Active|Inactive") String status // We will not expose this data
) {
}
