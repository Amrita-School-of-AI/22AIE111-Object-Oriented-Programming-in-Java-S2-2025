package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testCheckSign() {
        App app = new App();
        assertEquals("Positive", app.checkSign(10));
        assertEquals("Negative", app.checkSign(-1));
        assertEquals("Zero", app.checkSign(0));
    }

    @Test
    void testGetDayName() {
        App app = new App();
        assertEquals("Monday", app.getDayName(1));
        assertEquals("Sunday", app.getDayName(7));
        assertEquals("Invalid day", app.getDayName(9));
    }

    @Test
    void testCalculateFactorial() {
        App app = new App();
        assertEquals(120, app.calculateFactorial(5));
        assertEquals(1, app.calculateFactorial(0));
        assertEquals(720, app.calculateFactorial(6));
    }

    @Test
    void testIsPrime() {
        App app = new App();
        assertTrue(app.isPrime(7), "7 should be prime");
        assertFalse(app.isPrime(8), "8 should not be prime");
        assertFalse(app.isPrime(1), "1 is not prime by definition");
        assertFalse(app.isPrime(-5), "Negative numbers are not prime");
    }

    @Test
    void testSumUntil() {
        App app = new App();
        assertEquals(15, app.sumUntil(5), "1+2+3+4+5 = 15");
        assertEquals(1, app.sumUntil(1), "Limit=1 => 1");
        assertEquals(55, app.sumUntil(10), "1+2+...+10 = 55");
    }
}
