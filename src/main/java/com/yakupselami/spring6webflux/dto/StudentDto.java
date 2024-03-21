package com.yakupselami.spring6webflux.dto;

import java.util.List;

public record StudentDto(String name, String email, List<CourseDto> courses) {
}
