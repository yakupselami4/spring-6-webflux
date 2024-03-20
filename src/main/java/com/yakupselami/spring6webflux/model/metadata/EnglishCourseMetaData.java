package com.yakupselami.spring6webflux.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class EnglishCourseMetaData extends CourseMetaData{
    private String level;
    private List<String> books;
}
