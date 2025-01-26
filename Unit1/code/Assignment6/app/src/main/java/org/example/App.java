package org.example;

import java.util.Scanner;

public class App {

    /**
     * TODO: Return true if 'number' is prime, otherwise return false.
     *
     * @param number the integer to check
     * @return whether 'number' is prime
     */
    public boolean isPrime(int number) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        App app = new App();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int input = scanner.nextInt();

        if (app.isPrime(input)) {
            System.out.println(input + " is prime.");
        } else {
            System.out.println(input + " is not prime.");
        }
    }
}

