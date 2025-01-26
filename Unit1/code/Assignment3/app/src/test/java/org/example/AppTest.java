package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testGetDataTypesInfo() {
        App app = new App();
        String info = app.getDataTypesInfo();

        // Basic checks to see if required data types are mentioned
        assertTrue(info.contains("byte"), "Should mention byte");
        assertTrue(info.contains("short"), "Should mention short");
        assertTrue(info.contains("int"), "Should mention int");
        assertTrue(info.contains("long"), "Should mention long");
        assertTrue(info.contains("float"), "Should mention float");
        assertTrue(info.contains("double"), "Should mention double");
        assertTrue(info.contains("char"), "Should mention char");
        assertTrue(info.contains("boolean"), "Should mention boolean");
    }
}

