package io.pilet.monitoring.sample;

import io.pilet.monitoring.sample.model.Person;
import io.pilet.monitoring.sample.util.RandomUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

@Log4j2
class BasicTests {

    @Test
    void personTests() {
        Person person = RandomUtils.generate();
        Assertions.assertNotNull(person);
    }

}
