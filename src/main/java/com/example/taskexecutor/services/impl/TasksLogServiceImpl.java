package com.example.taskexecutor.services.impl;

import com.example.taskexecutor.database.entities.Task;
import com.example.taskexecutor.database.entities.TaskLog;
import com.example.taskexecutor.database.repositories.TasksLogsRepository;
import com.example.taskexecutor.dto.TaskLogDTO;
import com.example.taskexecutor.services.TasksLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.taskexecutor.utils.MapperUtils.mapList;

@Service
@RequiredArgsConstructor
public class TasksLogServiceImpl implements TasksLogService {

    private final TasksLogsRepository logsRepository;

    @Override
    public List<TaskLogDTO> getLogsForTask(Long taskId) {
        return mapList(logsRepository.findAllByTaskIdOrderById(taskId), TaskLogDTO.class);
    }

    @Override
    public void saveLogForTask(Task task) {
        if (task != null) {
            logsRepository.save(createLogForTask(task));
        }
    }

    private TaskLog createLogForTask(Task task) {
        TaskLog log = new TaskLog();
        log.setTaskId(task.getId());
        log.setStatus(task.getStatus());
        return log;
    }
}
