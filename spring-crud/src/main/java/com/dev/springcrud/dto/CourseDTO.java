package com.dev.springcrud.dto;

import com.dev.springcrud.enums.Category;
import com.dev.springcrud.enums.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull @Length(min = 4, max = 100) String name,
        //@NotNull @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String category, // V.1 By using @Pattern, it works!
        @NotNull @Length(max = 10) @Enumerated(EnumType.STRING) String category, // V.2 By using @Enumerated, it works too!
        //@NotNull @Length(max = 10) @ValueOfEnum(enumClass = Category.class) String category,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons
//        @NotBlank @NotNull @Length(max = 10)  @Pattern(regexp = "Active|Inactive") String status // We will not expose this data
) {
}
