package com.example.taskexecutor.kafka;

import com.example.taskexecutor.database.repositories.TasksRepository;
import com.example.taskexecutor.dto.TaskDTO;
import com.example.taskexecutor.executor.TaskExecutorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TasksListener {

    private final TaskExecutorService taskExecutorService;

    private final Logger logger = LoggerFactory.getLogger(TasksListener.class);

    @KafkaListener(topics = "task-for-execution")
    public void handleFinishRegistration(TaskDTO message) {
        logger.info("Task listener accepted task {}", message.getTitle());
        taskExecutorService.executeTask(message);
    }

}
