package org.example;

public class App {

    /**
     * Person class with a 'name' field.
     */
    public static class Person {
        public String name;

        public Person(String name) {
            this.name = name;
        }
    }

    /**
     * TODO: Attempt to change an integer parameter inside this method
     * and show that it does NOT change outside the method.
     */
    public void changePrimitive(int num) {
        // TODO
    }

    /**
     * TODO: Change the 'name' field of the 'person' object.
     */
    public void changeReference(Person person) {
        // TODO
    }

    public static void main(String[] args) {
        App app = new App();

        // Demonstrate with primitive
        int myNumber = 10;
        System.out.println("Before changePrimitive: " + myNumber);
        app.changePrimitive(myNumber);
        System.out.println("After changePrimitive: " + myNumber);

        // Demonstrate with reference
        Person bob = new Person("Bob");
        System.out.println("Before changeReference: " + bob.name);
        app.changeReference(bob);
        System.out.println("After changeReference: " + bob.name);
    }
}

