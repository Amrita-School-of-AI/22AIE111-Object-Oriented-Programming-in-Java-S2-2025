package org.example;

import java.util.Arrays;

public class App {

    /**
     * TODO: Swap two integers via 'returning' them in a 2-element array.
     *
     * @param a first integer
     * @param b second integer
     * @return an array of two elements: [swappedA, swappedB]
     */
    public int[] swapByValue(int a, int b) {
        // TODO
        return new int[]{a, b};
    }

    /**
     * TODO: Swap two integers in-place by reference.
     * We'll assume the array has exactly 2 elements: arr[0], arr[1].
     *
     * @param arr array of two integers
     */
    public void swapByReference(int[] arr) {
        // TODO
    }

    public static void main(String[] args) {
        App app = new App();

        // Demonstrate swapByValue
        int a = 5, b = 10;
        int[] swapped = app.swapByValue(a, b);
        System.out.println("swapByValue results: " + Arrays.toString(swapped));

        // Demonstrate swapByReference
        int[] pair = {5, 10};
        app.swapByReference(pair);
        System.out.println("swapByReference results: " + Arrays.toString(pair));
    }
}

