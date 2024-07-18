package io.pilet.monitoring.sample.service;

import io.pilet.monitoring.sample.model.TaskRequest;
import io.pilet.monitoring.sample.model.TaskResponse;
import io.pilet.monitoring.sample.util.Json;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Log4j2
public class TaskService {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final Random random;

    public TaskService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.random = new Random();
    }

    @SneakyThrows
    @KafkaListener(topics = "sample.request")
    void onTaskRequest(String requestJson) {
        log.info("Processing task {}", requestJson);
        TaskRequest request = Json.map(requestJson, TaskRequest.class);
        Thread.sleep(random.nextLong(10000));
        TaskResponse response = new TaskResponse(request.id(), request.task(), "DONE");
        kafkaTemplate.send("sample.response", Json.of(response));
    }

    @KafkaListener(topics = "sample.response")
    void onTaskResponse(String responseJson) {
        log.info("On task response {}", responseJson);
    }
}
