package com.dev.springcrud;

import com.dev.springcrud.enums.Category;
import com.dev.springcrud.model.Course;
import com.dev.springcrud.model.Lesson;
import com.dev.springcrud.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular with Spring");
			c.setCategory(Category.FRONTEND);

			Lesson l = new Lesson();
			l.setName("Introduction");
			l.setShareableYoutubeUrl("dFg4yHju81");
			l.setCourse(c);
			c.getLessons().add(l);

			Lesson l1 = new Lesson();
			l1.setName("Introduction");
			l1.setShareableYoutubeUrl("dFg4yHju82");
			l1.setCourse(c);
			c.getLessons().add(l1);

			courseRepository.save(c);
		};
	}
}
