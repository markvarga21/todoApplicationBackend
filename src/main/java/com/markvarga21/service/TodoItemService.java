package com.markvarga21.service;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.entity.TodoItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoItemService {
    String saveTodoItem(TodoItemDto todoItemDto);
    List<TodoItemDto> getAllTodoItems();
    boolean todoItemAlreadyExistsWithName(String name);
    String deleteTodoItemById(Long id);
    String modifyTodoItemById(TodoItemDto todoItemDto, Long id);
}
