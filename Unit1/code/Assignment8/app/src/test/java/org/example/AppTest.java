package org.example;

import org.junit.jupiter.api.Test;
import org.example.references.App.Person;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testPrimitiveChange() {
        App app = new App();
        int original = 10;
        app.changePrimitive(original);
        // The original should remain the same
        assertEquals(10, original, "Primitive value should not change after the method call.");
    }

    @Test
    void testReferenceChange() {
        App app = new App();
        Person person = new Person("Alice");
        app.changeReference(person);
        // We expect the person's name to be changed inside the method
        assertEquals("Changed Name", person.name, "Person's name should be changed inside the method.");
    }
}

