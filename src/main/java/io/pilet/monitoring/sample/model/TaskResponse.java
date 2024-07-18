package io.pilet.monitoring.sample.model;

public record TaskResponse (
        String id,
        String task,
        String status
) {
}
