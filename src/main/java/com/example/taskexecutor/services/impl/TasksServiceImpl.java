package com.example.taskexecutor.services.impl;

import com.example.taskexecutor.database.entities.Task;
import com.example.taskexecutor.database.repositories.TasksRepository;
import com.example.taskexecutor.dto.TaskInfoDTO;
import com.example.taskexecutor.enums.TaskStatus;
import com.example.taskexecutor.exceptions.EntityNotFoundException;
import com.example.taskexecutor.services.TasksLogService;
import com.example.taskexecutor.services.TasksService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.taskexecutor.utils.MapperUtils.mapList;
import static com.example.taskexecutor.utils.MapperUtils.mapObject;

@Data
@Service
public class TasksServiceImpl implements TasksService {

    private static final long MAX_DURATION_MS = 5000L;

    private final Logger logger = LoggerFactory.getLogger(TasksServiceImpl.class);

    private final TasksRepository tasksRepository;

    private final TasksLogService logService;

    @Override
    public TaskInfoDTO getTaskInfoDTO(Long id) {
        Task task = tasksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task", id));
        return mapObject(task, TaskInfoDTO.class);
    }

    @Override
    public List<TaskInfoDTO> getAllTasksInfoDTO() {
        return mapList(tasksRepository.findAll(), TaskInfoDTO.class);
    }

    @Override
    public void saveTask(Task task, TaskStatus status) {
        if (task != null) {
            task.setStatus(status);
            tasksRepository.save(task);
            logService.saveLogForTask(task);
        }
    }

    @Override
    public boolean isTaskCorrect(Task task) {
        if (task.getDuration() < 1 || task.getDuration() > MAX_DURATION_MS) {
            logger.error("Task {} has incorrect duration: {}", task.getTitle(), task.getDuration());
            return false;
        }
        return true;
    }
}
