package com.markvarga21.service;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.entity.TodoItem;
import org.springframework.stereotype.Service;

@Service
public interface TodoItemService {
    String saveTodoItem(TodoItemDto todoItemDto);
}
