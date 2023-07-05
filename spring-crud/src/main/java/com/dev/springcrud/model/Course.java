package com.dev.springcrud.model;

import com.dev.springcrud.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' where id = ?") // This will work with the DELETE method, this is called SOFT DELETE
@Where(clause = "status = 'Active'") // This will filter and show only active courses
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

//    @NotBlank
    @NotNull
//    @Length(max = 10)
//    @Pattern(regexp = "Back-End|Front-End")
    @Column(length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    /**
     * TO-DO: ENUMS for category and Status
     * Classes 43 and 44 have bugs
     */

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Active|Inactive")
    @Column(length = 10, nullable = false)
    private String status = "Active";

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();
}
