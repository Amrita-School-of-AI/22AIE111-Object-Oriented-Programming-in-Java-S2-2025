package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    public void testSayHello() {
        App app = new App();
        String expected = "Hello World!";
        String actual = app.sayHello();
        assertEquals(expected, actual, "The sayHello method should return 'Hello World!'");
    }}
