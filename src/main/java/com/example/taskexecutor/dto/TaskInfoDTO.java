package com.example.taskexecutor.dto;

import com.example.taskexecutor.enums.TaskStatus;
import lombok.Data;

@Data
public class TaskInfoDTO {

    private Long id;

    private String title;

    private TaskStatus status;

    private Long duration;

}
