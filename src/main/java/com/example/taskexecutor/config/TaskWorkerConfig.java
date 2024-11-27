package com.example.taskexecutor.config;

import com.example.taskexecutor.executor.TaskWorker;
import com.example.taskexecutor.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
public class TaskWorkerConfig {

    private final TasksService tasksService;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public TaskWorker taskWorker() {
        return new TaskWorker(tasksService);
    }
}
