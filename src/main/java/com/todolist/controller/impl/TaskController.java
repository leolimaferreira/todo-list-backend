package com.todolist.controller.impl;

import com.todolist.controller.GenericController;
import com.todolist.dtos.task.SaveTaskDTO;
import com.todolist.dtos.task.TaskSearchResponseDTO;
import com.todolist.dtos.task.UpdateTaskDTO;
import com.todolist.service.TaskService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController implements GenericController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskSearchResponseDTO> save(@RequestBody SaveTaskDTO dto) {
        TaskSearchResponseDTO savedTask = taskService.saveTask(dto);
        URI location = generateHeaderLocation(savedTask.id());
        return ResponseEntity.created(location).body(savedTask);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskSearchResponseDTO> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskSearchResponseDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskSearchResponseDTO> update(@PathVariable("id") UUID id, @RequestBody UpdateTaskDTO dto) {
        TaskSearchResponseDTO updatedTask = taskService.updateTask(id, dto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
