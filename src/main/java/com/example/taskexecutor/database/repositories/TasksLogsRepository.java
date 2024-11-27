package com.example.taskexecutor.database.repositories;

import com.example.taskexecutor.database.entities.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksLogsRepository extends JpaRepository<TaskLog, Long> {

    List<TaskLog> findAllByTaskIdOrderById(Long taskId);
}
