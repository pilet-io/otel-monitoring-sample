package io.pilet.monitoring.sample.util;

import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MD5 {

    @SneakyThrows
    public static String hashOf(Object... obj) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String text = Arrays.stream(obj).map(Object::toString).collect(Collectors.joining(","));
        md.update(text.getBytes());
        return String.format("%032x", new BigInteger(1, md.digest()));
    }
}
