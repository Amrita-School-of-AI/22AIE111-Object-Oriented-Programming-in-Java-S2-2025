package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testSayWelcome() {
        App app = new App();
        String expected = "Welcome to Java Programming";
        String actual = app.sayWelcome();
        assertEquals(expected, actual, "sayWelcome() should return the correct welcome message.");
    }

    @Test
    void testSum() {
        App app = new App();
        int expected = 30;
        int actual = app.sum(10, 20);
        assertEquals(expected, actual, "sum(10, 20) should return 30.");
    }
}

