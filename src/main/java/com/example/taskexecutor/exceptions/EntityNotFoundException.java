package com.example.taskexecutor.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

    String entityName;

    String entityId;

    public EntityNotFoundException(String entityName, Long entityId) {
        super();
        this.entityName = entityName;
        this.entityId = String.valueOf(entityId);
    }

    public EntityNotFoundException(String entityName, String entityId) {
        super();
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public String getMessage() {
        return "Entity " + entityName + " with identifier " + entityId + " not found";
    }
}

