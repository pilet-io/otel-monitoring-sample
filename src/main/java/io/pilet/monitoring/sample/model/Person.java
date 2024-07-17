package io.pilet.monitoring.sample.model;

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
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((name + age + zip).getBytes());
        String id = String.format("%032x", new BigInteger(1, md.digest()));
        return new Person(id, name, age, zip);
    }
}
