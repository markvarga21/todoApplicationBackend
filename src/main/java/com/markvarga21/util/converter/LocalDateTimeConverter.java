package com.markvarga21.util.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class LocalDateTimeConverter {
    private final DateTimeFormatter formatter;

    public String convertDateToString(LocalDateTime dateToFormat) {
        return dateToFormat.format(this.formatter);
    }

    public LocalDateTime convertDateStringToLocalDateTime(String dateToConvert) {
        log.info("stringDate: {}", dateToConvert);
        return LocalDateTime.parse(dateToConvert, this.formatter);
    }
}
