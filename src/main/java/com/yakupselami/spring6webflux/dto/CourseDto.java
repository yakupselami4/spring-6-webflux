package com.yakupselami.spring6webflux.dto;

import com.yakupselami.spring6webflux.model.metadata.CourseMetaData;

public record CourseDto(String name, String description, String teacher, Integer duration,
                        CourseMetaData courseMetaData) {



}
