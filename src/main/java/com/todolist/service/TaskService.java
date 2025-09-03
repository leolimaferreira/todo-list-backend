package com.todolist.service;

import com.todolist.dtos.SaveTaskDTO;
import com.todolist.dtos.TaskSearchResponseDTO;
import com.todolist.mapper.TaskMapper;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
