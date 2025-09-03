package com.todolist.mapper;

import com.todolist.dtos.SaveTaskDTO;
import com.todolist.dtos.TaskSearchResponseDTO;
import com.todolist.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(SaveTaskDTO dto);

    TaskSearchResponseDTO toTaskSearchResponseDTO(Task task);
}
