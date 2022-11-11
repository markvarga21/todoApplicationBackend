package com.markvarga21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoLocationDto {
    private String country;
    private String city;
    private String street;
    private String number;
}
