package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testAreaAndPerimeter() {
        // Sample test using length=5, width=3
        App rectangle = new App(5, 3);

        assertEquals(15, rectangle.getArea(), "Area should be 15 for length=5, width=3");
        assertEquals(16, rectangle.getPerimeter(), "Perimeter should be 16 for length=5, width=3");
    }
}

