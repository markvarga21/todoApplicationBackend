package com.markvarga21.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class TodoApplicationConfiguration {
    @Value("${date.format}")
    private String format;

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern(this.format);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
