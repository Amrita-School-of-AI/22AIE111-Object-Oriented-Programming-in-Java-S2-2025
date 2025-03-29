/*
 * Assignment: Implement a vehicle inheritance structure with method overriding.
 */
package org.example;

// Vehicle.java
public class Vehicle {
    public String startEngine() {
        return "Engine started";
    }
}

// Car.java
public class Car extends Vehicle {
    @Override
    public String startEngine() {
        // TODO: Add "Car" prefix to parent's message
        return "";
    }
}
