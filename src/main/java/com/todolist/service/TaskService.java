package com.todolist.service;

import com.todolist.dtos.task.SaveTaskDTO;
import com.todolist.dtos.task.TaskSearchResponseDTO;
import com.todolist.dtos.task.UpdateTaskDTO;
import com.todolist.exception.NotFoundException;
import com.todolist.mapper.TaskMapper;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskSearchResponseDTO saveTask(SaveTaskDTO dto) {
        Task task = taskMapper.toTask(dto);
        taskRepository.save(task);
        return taskMapper.toTaskSearchResponseDTO(task);
    }

    public TaskSearchResponseDTO getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        return taskMapper.toTaskSearchResponseDTO(task);
    }

    public List<TaskSearchResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toTaskSearchResponseDTOList(tasks);
    }

    public TaskSearchResponseDTO updateTask(UUID id, UpdateTaskDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        taskMapper.updateTaskFromDTO(dto, task);
        taskRepository.save(task);
        return taskMapper.toTaskSearchResponseDTO(task);
    }

    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        taskRepository.delete(task);
    }

}
