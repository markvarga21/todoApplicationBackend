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
        if (this.todoItemAlreadyExistsWithName(todoItemDto.getTitle())) {
            return String.format("Todo item with name '%s' already exists!", todoItemDto.getTitle());
        }
        this.todoItemRepository.save(todoItemToSave);
        return String.format("%s saved successfully!", todoItemDto);
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

    @Override
    public String deleteTodoItemById(Long id) {
        var todoItemOptional = this.todoItemRepository.findById(id);
        if (todoItemOptional.isEmpty()) {
            return String.format("Todo item not found with id '%d'!", id);
        }
        this.todoItemRepository.deleteById(todoItemOptional.get().getId());
        return String.format("Todo item with id '%d' deleted successfully!", id);
    }

    @Override
    public String modifyTodoItemById(TodoItemDto newTodoItem, Long id) {
        var todoItemEntityOptional = this.todoItemRepository.findById(id);
        if (todoItemEntityOptional.isEmpty()) {
            return String.format("Todo item not found with id '%d'!", id);
        }
        var todoItemToUpdate = todoItemEntityOptional.get();
        var newTodoItemEntity = this.todoItemMapper.mapTodoItemDtoToEntity(newTodoItem);
        todoItemToUpdate.setLocation(newTodoItemEntity.getLocation());
        todoItemToUpdate.setTitle(newTodoItem.getTitle());
        todoItemToUpdate.setDescription(newTodoItem.getDescription());
        this.todoItemRepository.save(todoItemToUpdate);
        return String.format("Todo item with id '%d' modified successfully!", id);
    }
}
