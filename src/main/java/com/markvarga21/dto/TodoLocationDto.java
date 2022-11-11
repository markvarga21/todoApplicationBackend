package com.markvarga21.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoLocationDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private Long number;
}
