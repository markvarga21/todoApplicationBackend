package com.markvarga21.controller;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoItemController {
    private final TodoItemService todoItemService;

    @GetMapping("/getTodoItems")
    public List<TodoItemDto> getTodoItems() {
        return this.todoItemService.getAllTodoItems();
    }

    @PostMapping("/saveTodoItem")
    public ResponseEntity<String> saveTodoItem(@RequestBody TodoItemDto todoItemDto) {
        return new ResponseEntity<>(this.todoItemService.saveTodoItem(todoItemDto), HttpStatus.CREATED);
    }
}
