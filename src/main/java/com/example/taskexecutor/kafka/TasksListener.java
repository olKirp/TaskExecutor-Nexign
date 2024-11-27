package com.example.taskexecutor.kafka;

import com.example.taskexecutor.dto.TaskDTO;
import com.example.taskexecutor.executor.TaskExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TasksListener {

    private final TaskExecutorService taskExecutorService;

    @KafkaListener(topics = "task-for-execution")
    public void handleFinishRegistration(TaskDTO message) {
        taskExecutorService.executeTask(message);
    }

}
