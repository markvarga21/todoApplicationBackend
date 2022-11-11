package com.markvarga21.controller;

import com.markvarga21.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoItemController {
    private final TodoItemService todoItemService;
}
