package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testArithmetic() {
        App app = new App();
        // Checking actual arithmetic results:
        assertEquals(13, app.add(10, 3), "10 + 3 should be 13");
        assertEquals(7, app.subtract(10, 3), "10 - 3 should be 7");
        assertEquals(30, app.multiply(10, 3), "10 * 3 should be 30");
        assertEquals(3, app.divide(10, 3), "10 / 3 (integer division) should be 3");
        assertEquals(1, app.modulus(10, 3), "10 % 3 should be 1");
    }

    @Test
    void testRelational() {
        App app = new App();
        // Checking relational results:
        assertTrue(app.greaterThan(10, 3), "10 > 3 should be true");
        assertTrue(app.equalTo(10, 10), "10 == 10 should be true");
        assertTrue(app.lessThanOrEqual(3, 3), "3 <= 3 should be true");
        assertFalse(app.greaterThan(3, 10), "3 > 10 should be false");
    }

    @Test
    void testLogical() {
        App app = new App();
        // Checking logical results:
        assertFalse(app.logicalAnd(true, false), "true && false should be false");
        assertTrue(app.logicalOr(true, false), "true || false should be true");
        assertFalse(app.logicalNot(true), "!true should be false");
    }

    @Test
    void testBitwise() {
        App app = new App();
        // Checking bitwise results:
        // 5 = 0101 (binary), 3 = 0011
        assertEquals(1, app.bitwiseAnd(5, 3), "0101 & 0011 = 0001 -> 1");
        assertEquals(7, app.bitwiseOr(5, 3), "0101 | 0011 = 0111 -> 7");
        assertEquals(6, app.bitwiseXor(5, 3), "0101 ^ 0011 = 0110 -> 6");
        // ~5 = ... depends on integer representation, but typically ~0101 -> ...11111010 in 32-bit
        // We can check specifically if it matches the two's complement result
        assertEquals(~5, app.bitwiseNot(5), "bitwise NOT of 5 should match ~5 in Java");
        assertEquals(10, app.leftShift(5, 1), "0101 << 1 = 1010 -> 10");
        assertEquals(2, app.rightShift(5, 1), "0101 >> 1 = 0010 -> 2");
    }
}

