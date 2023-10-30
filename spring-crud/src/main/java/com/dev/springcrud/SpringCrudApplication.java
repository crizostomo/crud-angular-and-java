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

			for (int i = 1; i < 5; i++) {
				Course c = new Course();
				c.setName("Course " + i);
				c.setCategory(Category.FRONTEND);
				c.setStatus("Active");

				for (int j = 1; j < 10; j++) {
					Lesson lesson = new Lesson();
					lesson.setName("Lesson " + j);
					lesson.setShareableYoutubeUrl("Fj3Zvf-N4bk");
					c.getLessons().add(lesson);
				}

				courseRepository.save(c);
			}
		};
	}
}