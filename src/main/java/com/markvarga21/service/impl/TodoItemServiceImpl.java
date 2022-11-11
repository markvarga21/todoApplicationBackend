package com.markvarga21.service.impl;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.entity.TodoItem;
import com.markvarga21.repository.TodoItemRepository;
import com.markvarga21.service.TodoItemService;
import com.markvarga21.util.mapping.TodoItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemRepository todoItemRepository;
    private final TodoItemMapper todoItemMapper;

    @Override
    public String saveTodoItem(TodoItemDto todoItemDto) {
        TodoItem todoItemToSave = this.todoItemMapper.mapTodoItemDtoToEntity(todoItemDto);
        log.info("Todo item dto: {}", todoItemDto);
        log.info("Todo item entity: {}", todoItemToSave);
        if (this.todoItemAlreadyExistsWithName(todoItemDto.getTitle())) {
            return String.format("Todo item with name '%s' already exists!", todoItemDto.getTitle());
        }
        this.todoItemRepository.save(todoItemToSave);
        return String.format("%s saved successfully!", todoItemToSave);
    }

    @Override
    public boolean todoItemAlreadyExistsWithName(String name) {
        return this.todoItemRepository
                .findAll()
                .stream()
                .anyMatch(todoItem -> todoItem.getTitle().equals(name));
    }

    @Override
    public List<TodoItemDto> getAllTodoItems() {
        return this.todoItemRepository
                .findAll()
                .stream()
                .map(this.todoItemMapper::mapTodoItemEntityToDto)
                .toList();
    }
}
