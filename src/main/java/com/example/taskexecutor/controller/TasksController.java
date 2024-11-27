package com.example.taskexecutor.controller;

import com.example.taskexecutor.dto.TaskInfoDTO;
import com.example.taskexecutor.dto.TaskLogDTO;
import com.example.taskexecutor.services.TasksLogService;
import com.example.taskexecutor.services.impl.TasksServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksServiceImpl tasksService;

    private final TasksLogService tasksLogService;

    @GetMapping(value = "/info")
    public List<TaskInfoDTO> getAllTasks() {
        return tasksService.getAllTasksInfoDTO();
    }

    @GetMapping(value = "/info/{taskId}")
    public TaskInfoDTO getTaskInfo(@PathVariable Long taskId) {
        return tasksService.getTaskInfoDTO(taskId);
    }

    @GetMapping(value = "/logs/{taskId}")
    public List<TaskLogDTO> getTaskLogs(@PathVariable Long taskId) {
        return tasksLogService.getLogsForTask(taskId);
    }
}
