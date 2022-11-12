package com.markvarga21.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class TodoItemDto {
    private Long id;
    private String title;
    private String description;
    private String date;
    private TodoLocationDto location;
}
