package com.example.taskexecutor.services;

import com.example.taskexecutor.database.entities.Task;
import com.example.taskexecutor.dto.TaskInfoDTO;
import com.example.taskexecutor.enums.TaskStatus;

import java.util.List;

public interface TasksService {

    TaskInfoDTO getTaskInfoDTO(Long id);

    List<TaskInfoDTO> getAllTasksInfoDTO();

    void saveTask(Task task, TaskStatus status);

    boolean isTaskCorrect(Task task);

}
