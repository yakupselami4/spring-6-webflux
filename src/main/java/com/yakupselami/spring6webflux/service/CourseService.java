package com.yakupselami.spring6webflux.service;

import com.yakupselami.spring6webflux.dto.CourseDto;
import com.yakupselami.spring6webflux.model.Course;
import com.yakupselami.spring6webflux.repository.CourseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    public Mono<CourseDto> findById(UUID id) {
        return courseRepository.findById(id)
                .map(course -> new CourseDto(course.getName(), course.getDescription(), course.getTeacher(), course.getDuration(),course.getCourseMetaData()));
    }
}
