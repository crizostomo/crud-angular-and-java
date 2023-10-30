package com.dev.springcrud.repository;

import com.dev.springcrud.enums.Status;
import com.dev.springcrud.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByStatus(Pageable pageable, Status status);

    List<Course> findByName(String name);
}