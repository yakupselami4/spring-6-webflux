package com.yakupselami.spring6webflux.model.metadata;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.yakupselami.spring6webflux.model.Course;
import com.yakupselami.spring6webflux.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = SpringCourseMetaData.class,name = CourseConstants.SPRING_COURSE_TYPE),
                @JsonSubTypes.Type(value = EnglishCourseMetaData.class,name = CourseConstants.ENGLISH_COURSE_TYPE)
        }
)
@SuperBuilder
public class CourseMetaData {

    @JsonIgnore
    private String type;



}
