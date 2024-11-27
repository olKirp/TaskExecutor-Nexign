package com.example.taskexecutor.executor;

import com.example.taskexecutor.database.entities.Task;
import com.example.taskexecutor.enums.TaskStatus;
import com.example.taskexecutor.services.TasksService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@RequiredArgsConstructor
public class TaskWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TaskWorker.class);

    private final TasksService tasksService;

    private Task task;

    @Override
    public void run() {
        if(!isTaskCorrect(task))
            return;
        tasksService.saveTask(task, TaskStatus.CREATED);
        executeTask();
    }

    private void executeTask() {
        try {
            tasksService.saveTask(task, TaskStatus.IN_PROGRESS);
            Thread.sleep(task.getDuration());
            tasksService.saveTask(task, TaskStatus.FINISHED);
        } catch (InterruptedException e) {
            logger.error("Exception during execution of the task {}", task.getTitle(), e);
            tasksService.saveTask(task, TaskStatus.ERROR);
        }
    }

    private boolean isTaskCorrect(Task task) {
        if (task == null) {
            logger.error("Task null cannot be executed");
            return false;
        }
        if (!tasksService.isTaskCorrect(task)) {
            tasksService.saveTask(task, TaskStatus.ERROR);
            return false;
        }
        return true;
    }
}
