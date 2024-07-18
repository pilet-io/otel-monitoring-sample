package io.pilet.monitoring.sample.model;

import io.pilet.monitoring.sample.util.MD5;

import java.util.Map;
import java.util.UUID;

public record TaskRequest (
        String id,
        String task,
        String args
) {

    public static TaskRequest of(String task) {
        String args = UUID.randomUUID().toString();
        String id = MD5.hashOf(task, args);
        return new TaskRequest(id, task, args);
    }
}
