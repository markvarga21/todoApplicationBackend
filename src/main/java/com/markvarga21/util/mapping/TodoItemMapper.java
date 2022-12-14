package com.markvarga21.util.mapping;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.entity.TodoItem;
import com.markvarga21.util.converter.LocalDateTimeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TodoItemMapper {
    private final ModelMapper modelMapper;
    private final TodoLocationMapper todoLocationMapper;
    private final LocalDateTimeConverter converter;

    public TodoItem mapTodoItemDtoToEntity(TodoItemDto todoItemDto) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(todoItemDto.getTitle());
        todoItem.setDescription(todoItemDto.getDescription());
//        todoItem.setLocation(this.todoLocationMapper.mapTodoLocationDtoToEntity(todoItemDto.getLocation()));
        todoItem.setLocation(todoItemDto.getLocation());
        log.info("todoitem title: {}", todoItemDto.getTitle());
        log.info("todoitem dto date: {}", todoItemDto.getDate());
        todoItem.setDate(this.converter.convertDateStringToLocalDateTime(todoItemDto.getDate()));
        todoItem.setId(todoItemDto.getId());
        return todoItem;
    }

    public TodoItemDto mapTodoItemEntityToDto(TodoItem todoItem) {
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setTitle(todoItem.getTitle());
        todoItemDto.setDescription(todoItem.getDescription());
//        todoItemDto.setLocation(this.todoLocationMapper.mapTodoLocationEntityToDto(todoItem.getLocation()));
        todoItemDto.setLocation(todoItem.getLocation());
        todoItemDto.setDate(this.converter.convertDateToString(todoItem.getDate()));
        todoItemDto.setId(todoItem.getId());
        return todoItemDto;
    }
}
