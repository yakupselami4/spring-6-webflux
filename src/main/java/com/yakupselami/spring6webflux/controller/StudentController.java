package com.yakupselami.spring6webflux.controller;

import com.yakupselami.spring6webflux.model.Student;
import com.yakupselami.spring6webflux.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Flux<Student> findAll(){
        return studentService.findAll();
    }

}
