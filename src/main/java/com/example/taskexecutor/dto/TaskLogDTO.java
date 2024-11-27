package com.example.taskexecutor.dto;

import com.example.taskexecutor.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskLogDTO {

    private Long id;

    private Long taskId;

    private TaskStatus status;

    private Date timestamp;

}
