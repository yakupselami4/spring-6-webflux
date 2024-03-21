package com.yakupselami.spring6webflux.controller;


import com.yakupselami.spring6webflux.model.Course;
import com.yakupselami.spring6webflux.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Flux<Course> findAll(){
        return courseService.findAll();
    }


}
