package io.pilet.monitoring.sample.generator;

import io.pilet.monitoring.sample.model.Person;
import io.pilet.monitoring.sample.model.TaskRequest;
import io.pilet.monitoring.sample.util.Json;
import io.pilet.monitoring.sample.util.RandomUtils;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ClientLoadGenerator {
    private final URI PEOPLE_URL;
    HttpClient httpClient = HttpClient.newBuilder().build();
    private final KafkaTemplate<String,String> kafkaTemplate;

    @SneakyThrows
    public ClientLoadGenerator(ServerProperties serverProperties, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        String url = String.format("http://localhost:%d/people", serverProperties.getPort());
        PEOPLE_URL = new URI(url);
    }


    @SneakyThrows
    @Scheduled(cron = "*/5 * * * * *")
    void listPeople() {
        HttpRequest request = HttpRequest.newBuilder().uri(PEOPLE_URL).GET().build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    @Scheduled(cron = "*/10 * * * * *")
    void createPerson() {
        Person person = RandomUtils.generate();
        HttpRequest request = HttpRequest.newBuilder().uri(PEOPLE_URL)
                .POST(HttpRequest.BodyPublishers.ofString(Json.of(person)))
                .header("Content-Type", "application/json")
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    @Scheduled(cron = "0 * * * * *")
    void requestTask() {
        TaskRequest taskRequest = TaskRequest.of("scheduled-task");
        kafkaTemplate.send("admin.sample.request", Json.of(taskRequest));
    }

}
