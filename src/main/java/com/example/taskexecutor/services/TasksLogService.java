package com.example.taskexecutor.services;

import com.example.taskexecutor.database.entities.Task;
import com.example.taskexecutor.dto.TaskLogDTO;

import java.util.List;

public interface TasksLogService {

    List<TaskLogDTO> getLogsForTask(Long taskId);

    void saveLogForTask(Task task);
}
