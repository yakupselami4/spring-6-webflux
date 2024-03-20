package com.yakupselami.spring6webflux.repository;

import com.yakupselami.spring6webflux.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface StudentRepository extends ReactiveCrudRepository<Student, UUID> {
}
