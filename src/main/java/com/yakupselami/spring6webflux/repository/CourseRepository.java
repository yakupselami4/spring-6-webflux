package com.yakupselami.spring6webflux.repository;

import com.yakupselami.spring6webflux.model.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CourseRepository extends ReactiveCrudRepository<Course, UUID> {






}
