package io.pilet.monitoring.sample.util;

import io.pilet.monitoring.sample.model.Person;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
    static Map<String, List<String>> collectionMap = new HashMap<>();
    static Random random = new Random();

    public static Integer intRange(int min, int max) {
        return random.nextInt(max-min) + min;
    }

    public static String intDigits(int len) {
        String max = "";
        for(int i=0; i<len; i++) {
            max += "9";
        }
        String format = "%0" + len + "d";
        return String.format(format, random.nextInt(Integer.parseInt(max)));
    }

    public static String randomInCollection(String collection) {
        List<String> list = collection(collection);
        return list.get(random.nextInt(list.size()));
    }

    public static Person generate() {
        String name = randomInCollection("first-name") + " "
                + randomInCollection("last-name");
        Integer age = intRange(18, 98);
        String zip = intDigits(5);
        return Person.of(name, age, zip);
    }

    @SneakyThrows
    public static List<String> collection(String name) {
        if(collectionMap.containsKey(name))
            return collectionMap.get(name);
        List<String> list = Files
                .lines(Paths.get("src/main/resources/sample/" + name + ".txt"))
                .toList();
        collectionMap.put(name, list);
        return list;

    }
}
