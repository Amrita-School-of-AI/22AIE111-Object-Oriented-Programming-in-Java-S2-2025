package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testSwapByValue() {
        App app = new App();
        int[] swapped = app.swapByValue(5, 10);
        // swapped[0] should be 10, swapped[1] should be 5
        assertEquals(10, swapped[0], "First element should now be 10");
        assertEquals(5, swapped[1], "Second element should now be 5");
    }

    @Test
    void testSwapByReference() {
        App app = new App();
        int[] pair = {5, 10};
        app.swapByReference(pair);
        // pair[0] should be 10, pair[1] should be 5
        assertEquals(10, pair[0], "First element should now be 10");
        assertEquals(5, pair[1], "Second element should now be 5");
    }
}

