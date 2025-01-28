package org.example;

import java.util.Scanner;

public class App {

    /**
     * TODO: Uses if-else to check if 'number' is positive, negative, or zero.
     * Return:
     *  - "Positive" if number > 0
     *  - "Negative" if number < 0
     *  - "Zero" if number == 0
     */
    public String checkSign(int number) {
        // TODO
        return "";
    }

    /**
     * TODO: Uses switch-case to return the day of the week:
     *    1 -> "Monday"
     *    2 -> "Tuesday"
     *    3 -> "Wednesday"
     *    4 -> "Thursday"
     *    5 -> "Friday"
     *    6 -> "Saturday"
     *    7 -> "Sunday"
     * Otherwise, return "Invalid day".
     */
    public String getDayName(int day) {
        // TODO
        return "";
    }

    /**
     * TODO: Uses a for loop to calculate factorial of n.
     * Factorial n! = 1 * 2 * 3 * ... * n
     * Assume n >= 0 (factorial(0) = 1)
     */
    public long calculateFactorial(int n) {
        // TODO
        return 0;
    }

    /**
     * TODO: Uses a while loop (or for loop) to check if 'number' is prime.
     * Return true if prime, false otherwise.
     * A prime number is a number > 1 that has no divisors other than 1 and itself.
     */
    public boolean isPrime(int number) {
        // TODO
        return false;
    }

    /**
     * TODO: Uses a do-while loop to sum integers from 1 up to 'limit'.
     * Example: sumUntil(5) => 1 + 2 + 3 + 4 + 5 = 15
     */
    public int sumUntil(int limit) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        App app = new App();

        // if-else
        System.out.println("checkSign(-10) = " + app.checkSign(-10));  // "Negative", e.g.
        System.out.println("checkSign(0) = " + app.checkSign(0));      // "Zero"
        System.out.println("checkSign(5) = " + app.checkSign(5));      // "Positive"

        // switch-case
        System.out.println("\ngetDayName(1) = " + app.getDayName(1));  // "Monday"
        System.out.println("getDayName(7) = " + app.getDayName(7));    // "Sunday"
        System.out.println("getDayName(9) = " + app.getDayName(9));    // "Invalid day"

        // for loop
        System.out.println("\ncalculateFactorial(5) = " + app.calculateFactorial(5)); // 120
        System.out.println("calculateFactorial(0) = " + app.calculateFactorial(0));   // 1

        // while loop
        System.out.println("\nisPrime(7) = " + app.isPrime(7));   // true
        System.out.println("isPrime(8) = " + app.isPrime(8));     // false
        System.out.println("isPrime(1) = " + app.isPrime(1));     // false

        // do-while
        System.out.println("\nsumUntil(5) = " + app.sumUntil(5)); // 15
    }
}

