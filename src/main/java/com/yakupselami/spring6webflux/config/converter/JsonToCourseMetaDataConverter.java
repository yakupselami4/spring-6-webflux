package com.yakupselami.spring6webflux.config.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakupselami.spring6webflux.model.metadata.CourseMetaData;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.io.IOException;
@ReadingConverter
@Slf4j
public class JsonToCourseMetaDataConverter implements Converter<Json, CourseMetaData> {

    private final ObjectMapper mapper;

    public JsonToCourseMetaDataConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public CourseMetaData convert(Json source) {
        try {
            return mapper.readValue(source.asString(), CourseMetaData.class);
        } catch (IOException e) {
            log.error("Error while converting JSON to CourseMetadata", e);
            throw new RuntimeException(e);
        }
    }
}
