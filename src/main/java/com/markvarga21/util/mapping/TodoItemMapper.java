package com.markvarga21.util.mapping;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.entity.TodoItem;
import com.markvarga21.util.converting.LocalDateTimeConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Destination;

@Component
@RequiredArgsConstructor
public class TodoItemMapper {
    private final ModelMapper modelMapper;
    private final TodoLocationMapper todoLocationMapper;
    private final LocalDateTimeConverter converter;

    public TodoItem mapTodoItemDtoToEntity(TodoItemDto todoItemDto) {
        TodoItem item = new TodoItem(
                todoItemDto.getTitle(),
                todoItemDto.getDescription(),
                this.converter.convertDateStringToLocalDateTime(todoItemDto.getDate()),
                this.todoLocationMapper.mapTodoLocationDtoToEntity(todoItemDto.getLocation())
        );
    }

    public TodoItemDto mapTodoItemEntityToDto(TodoItem todoItem) {
        return this.modelMapper.map(todoItem, TodoItemDto.class);
    }
}
