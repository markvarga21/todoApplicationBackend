package com.markvarga21.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoItemDto {
    private Long id;
    private String title;
    private String description;
    private String date;
    private String location;
}
