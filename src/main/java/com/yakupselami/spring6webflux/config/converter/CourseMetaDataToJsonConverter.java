package com.yakupselami.spring6webflux.config.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakupselami.spring6webflux.model.metadata.CourseMetaData;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
@Slf4j
public class CourseMetaDataToJsonConverter implements Converter<CourseMetaData, Json> {

    private final ObjectMapper objectMapper;

    public CourseMetaDataToJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Json convert(CourseMetaData source) {
        try {
            return Json.of(objectMapper.writeValueAsBytes(source));
        } catch (JsonProcessingException e) {
            log.error("Error while converting CourseMetaData to JSON",e);
            throw new RuntimeException(e);
        }
    }
}
