package com.example.taskexecutor.executor;

import com.example.taskexecutor.database.entities.Task;
import com.example.taskexecutor.dto.TaskDTO;
import com.example.taskexecutor.utils.MapperUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

@Service
@Data
@RequiredArgsConstructor
public class TaskExecutorService {

    private final ObjectFactory<TaskWorker> taskWorkerObjectFactory;

    private final ThreadPoolExecutor executor;

    public void executeTask(TaskDTO taskDTO) {
        TaskWorker worker = taskWorkerObjectFactory.getObject();
        worker.setTask(MapperUtils.mapObject(taskDTO, Task.class));
        executor.execute(worker);
    }
}
