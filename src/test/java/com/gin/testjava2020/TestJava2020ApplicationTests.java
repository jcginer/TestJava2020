package com.gin.testjava2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestJava2020ApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(this::doNotThrowException);
    }

    private void doNotThrowException() {
    }

}
