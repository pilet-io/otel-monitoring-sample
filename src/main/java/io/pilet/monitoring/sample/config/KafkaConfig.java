package io.pilet.monitoring.sample.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic requestTopic() {
        return TopicBuilder.name("sample.request").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic responseTopic() {
        return TopicBuilder.name("sample.response").partitions(1).replicas(1).build();
    }
}
