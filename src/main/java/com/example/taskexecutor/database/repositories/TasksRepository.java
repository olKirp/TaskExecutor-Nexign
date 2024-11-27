package com.example.taskexecutor.database.repositories;

import com.example.taskexecutor.database.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {
}
