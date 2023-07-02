package com.dev.springcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(
        @JsonProperty("_id") Long ud,
        @NotBlank @NotNull @Length(min = 4, max = 100) String name,
        @NotBlank @NotNull @Length(max = 10) @Pattern(regexp = "Back-End|Front-End") String category
//        @NotBlank @NotNull @Length(max = 10)  @Pattern(regexp = "Active|Inactive") String status // We will not expose this data
) {
}
