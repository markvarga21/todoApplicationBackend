package com.markvarga21.controller;

import com.markvarga21.dto.TodoItemDto;
import com.markvarga21.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoItemController {
    private final TodoItemService todoItemService;

    @PreAuthorize("hasAuthority('SCOPE_superadmin')")
    @GetMapping("/getTodoItems")
    public ResponseEntity<List<TodoItemDto>> getTodoItems() {
        return new ResponseEntity<>(
                this.todoItemService.getAllTodoItems(),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority('SCOPE_user')")
    @PostMapping("/save")
    public ResponseEntity<String> saveTodoItem(@RequestBody TodoItemDto todoItemDto) {
        return new ResponseEntity<>(this.todoItemService.saveTodoItem(todoItemDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTodoItem(@RequestParam Long id) {
        return new ResponseEntity<>(this.todoItemService.deleteTodoItemById(id), HttpStatus.GONE);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/update")
    public ResponseEntity<String> modifyTodoItem(@RequestBody TodoItemDto todoItemDto, @RequestParam Long id) {
        return new ResponseEntity<>(this.todoItemService.modifyTodoItemById(todoItemDto, id), HttpStatus.OK);
    }
}
