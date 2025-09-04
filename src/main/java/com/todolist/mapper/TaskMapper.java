package com.todolist.mapper;

import com.todolist.dtos.task.SaveTaskDTO;
import com.todolist.dtos.task.TaskSearchResponseDTO;
import com.todolist.dtos.task.UpdateTaskDTO;
import com.todolist.model.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(SaveTaskDTO dto);

    TaskSearchResponseDTO toTaskSearchResponseDTO(Task task);

    List<TaskSearchResponseDTO> toTaskSearchResponseDTOList(List<Task> tasks);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "completed", source = "completed")
    void updateTaskFromDTO(UpdateTaskDTO dto, @MappingTarget Task task);
}
