package io.pilet.monitoring.sample.model;

import io.pilet.monitoring.sample.util.MD5;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;

public record Person (
        String id,
        String name,
        Integer age,
        String zip
) {

    @SneakyThrows
    public static Person of(String name, Integer age, String zip) {
        String id = MD5.hashOf(name, age, zip);
        return new Person(id, name, age, zip);
    }
}
