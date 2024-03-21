package com.yakupselami.spring6webflux;

import com.yakupselami.spring6webflux.model.Course;
import com.yakupselami.spring6webflux.model.Student;
import com.yakupselami.spring6webflux.model.metadata.SpringCourseMetaData;
import com.yakupselami.spring6webflux.repository.CourseRepository;
import com.yakupselami.spring6webflux.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class Spring6WebfluxApplication implements CommandLineRunner {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public Spring6WebfluxApplication(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Spring6WebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course = Course.builder()
                .id(UUID.randomUUID())
                .name("Webflux")
                .description("Spring 6 webflux reactive programming course")
                .duration(10)
                .teacher("Folksdev")
                .courseMetaData(SpringCourseMetaData.builder()
                        .type("spring")
                        .language("java")
                        .github("github.com/folksdev")
                        .build())
                .isUpdated(false)
                .build();

        //courseRepository.save(course).block(); // save işleminde block koyulabilir

        Student student = Student.builder()
                .name("John")
                .email("john@email.com")
                .dateOfBirth(LocalDate.of(2000,1,1))
                .courses(Set.of(course.getId().toString()))
                .isUpdated(false)
                .build();

        //studentRepository.save(student).block();
    }
}
