package com.todolist.controller.impl;

import com.todolist.controller.GenericController;
import com.todolist.dtos.SaveTaskDTO;
import com.todolist.dtos.TaskSearchResponseDTO;
import com.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TaskController implements GenericController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskSearchResponseDTO> save(@RequestBody SaveTaskDTO dto) {
        TaskSearchResponseDTO savedTask = taskService.saveTask(dto);
        URI location = generateHeaderLocation(savedTask.id());
        return ResponseEntity.created(location).body(savedTask);
    }

}
