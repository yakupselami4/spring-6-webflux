package com.yakupselami.spring6webflux.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakupselami.spring6webflux.config.converter.CourseMetaDataToJsonConverter;
import com.yakupselami.spring6webflux.config.converter.JsonToCourseMetaDataConverter;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import io.r2dbc.pool.ConnectionPool;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties({R2dbcProperties.class, FlywayProperties.class})
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Value("${spring-6-weblux.database.host}")
    private String host;

    @Value("${spring-6-weblux.database.port}")
    private int port;

    @Value("${spring-6-weblux.database.name}")
    private String database;

    @Value("${spring-6-weblux.database.schema}")
    private String schema;

    @Value("${spring-6-weblux.database.username}")
    private String username;

    @Value("${spring-6-weblux.database.password}")
    private String password;

    @Value("${spring-6-weblux.database.pool.size.initial}")
    private int initialPoolSize;

    @Value("${spring-6-weblux.database.pool.size.max}")
    private int maxPoolSize;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        final PostgresqlConnectionFactory connectionFactory = new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(host)
                        .port(port)
                        .database(database)
                        .schema(schema)
                        .username(username)
                        .password(password)
                        .build()
        );

        final ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration
                .builder(connectionFactory)
                .initialSize(initialPoolSize)
                .maxSize(maxPoolSize)
                .build();

        return new ConnectionPool(poolConfiguration);
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory){
        return new R2dbcTransactionManager(connectionFactory);

    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    @Override
    public R2dbcCustomConversions r2dbcCustomConversions(){

        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new CourseMetaDataToJsonConverter(objectMapper()));
        converters.add(new JsonToCourseMetaDataConverter(objectMapper()));
        return new R2dbcCustomConversions(getStoreConversions(),converters);
    }

}
