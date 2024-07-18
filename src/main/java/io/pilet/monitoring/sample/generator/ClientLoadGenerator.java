package io.pilet.monitoring.sample.generator;

import io.pilet.monitoring.sample.model.Person;
import io.pilet.monitoring.sample.util.Json;
import io.pilet.monitoring.sample.util.RandomUtils;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public ClientLoadGenerator() {
        PEOPLE_URL = new URI("http://localhost:8099/people");
    }


    @SneakyThrows
    @Scheduled(cron = "*/5 * * * * *")
    void list() {
        HttpRequest request = HttpRequest.newBuilder().uri(PEOPLE_URL).GET().build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    @Scheduled(cron = "*/10 * * * * *")
    void create() {
        Person person = RandomUtils.generate();
        HttpRequest request = HttpRequest.newBuilder().uri(PEOPLE_URL)
                .POST(HttpRequest.BodyPublishers.ofString(Json.of(person)))
                .header("Content-Type", "application/json")
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
