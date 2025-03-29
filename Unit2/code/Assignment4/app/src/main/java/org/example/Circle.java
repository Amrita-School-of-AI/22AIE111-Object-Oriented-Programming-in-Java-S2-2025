/*
 * Assignment: Implement a Drawable interface with Circle class.
 */
package org.example;

// Drawable.java
public interface Drawable {
    String draw();
}

// Circle.java
public class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        // TODO: Initialize
    }
    
    @Override
    public String draw() {
        // TODO: Return "Drawing circle with radius X"
        return "";
    }
}
