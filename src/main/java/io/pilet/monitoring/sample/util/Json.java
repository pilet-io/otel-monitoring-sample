package io.pilet.monitoring.sample.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.util.List;

public class Json {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ObjectMapper prettyMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setSerializationInclusion(Include.NON_NULL);


    @SneakyThrows
    public static String of(Object data) {
        return objectMapper.writeValueAsString(data);
    }

    @SneakyThrows
    public static String prettyOf(Object data) {
        return prettyMapper.writeValueAsString(data);
    }

    @SneakyThrows
    public static <T> T map(String json, Class<T> tClass) {
        return objectMapper.readValue(json, tClass);
    }


    public static ObjectMapper objectMapper() {
        return objectMapper;
    }

    @SneakyThrows
    public static <T> T map(String json, TypeReference<T> typeReference) {
        return objectMapper.readValue(json, typeReference);
    }

    @SneakyThrows
    public static <T> List<T> mapList(String json, Class<T> tClass) {
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, tClass);
        return objectMapper.readValue(json, type);
    }

    public static Object mapObject(String json) {
        return map(json, Object.class);
    }
}
