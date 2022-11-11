package com.markvarga21.service.impl;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.entity.TodoItem;
import com.markvarga21.repository.TodoItemRepository;
import com.markvarga21.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemRepository todoItemRepository;

    @Override
    public String saveTodoItem(TodoItemDto todoItemDto) {
        return null;
    }
}
