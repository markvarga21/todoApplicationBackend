package com.markvarga21.util.mapping;

import com.markvarga21.dto.TodoLocationDto;
import com.markvarga21.entity.TodoLocation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoLocationMapper {
    private final ModelMapper mapper;

    public TodoLocation mapTodoLocationDtoToEntity(TodoLocationDto todoLocationDto) {
        return this.mapper.map(todoLocationDto, TodoLocation.class);
    }

    public TodoLocationDto mapTodoLocationEntityToDto(TodoLocation todoLocation) {
        return this.mapper.map(todoLocation, TodoLocationDto.class);
    }
}
