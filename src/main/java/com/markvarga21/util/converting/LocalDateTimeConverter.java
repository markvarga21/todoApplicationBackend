package com.markvarga21.util.converting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class LocalDateTimeConverter {
    private final DateTimeFormatter formatter;

    public String convertDateToString(LocalDateTime dateToFormat) {
        return dateToFormat.format(this.formatter);
    }

    public LocalDateTime convertDateStringToLocalDateTime(String dateToConvert) {
        return LocalDateTime.parse(dateToConvert, this.formatter);
    }
}
