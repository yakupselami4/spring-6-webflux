package com.yakupselami.spring6webflux.model.metadata;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class SpringCourseMetaData extends CourseMetaData{

    private List<String> prerequisites;
    private String language;
    private String github;
}
