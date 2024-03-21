package com.yakupselami.spring6webflux.service;


import com.yakupselami.spring6webflux.dto.CourseDto;
import com.yakupselami.spring6webflux.dto.StudentDto;
import com.yakupselami.spring6webflux.dto.StudentListDto;
import com.yakupselami.spring6webflux.model.Student;
import com.yakupselami.spring6webflux.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final CourseService courseService;
    private final StudentRepository studentRepository;

    public StudentService(CourseService courseService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<StudentListDto> findAllWithCourses() {
        return studentRepository.findAll()
                .flatMap(student -> {
                    List<Mono<CourseDto>> courseDtoList = student
                            .getCourses()
                            .stream()
                            .map(courseId-> courseService.findById(UUID.fromString(courseId)))
                            .collect(Collectors.toList());

                    return Flux.combineLatest(courseDtoList, objects -> {
                        List<CourseDto> courses = Arrays.stream(objects)
                                .map(obj -> (CourseDto) obj)
                                .collect(Collectors.toList());
                        return new StudentDto(student.getName(),student.getEmail(),courses);
                    });
                })
                .collectList()
                .map(StudentListDto::new);
    }
}
