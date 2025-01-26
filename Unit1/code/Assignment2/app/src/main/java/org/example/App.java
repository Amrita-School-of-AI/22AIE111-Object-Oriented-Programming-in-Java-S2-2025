package org.example;

import java.util.Scanner;

public class App {

    /**
     * TODO: Return the string "Welcome to Java Programming".
     *
     * @return The welcome message.
     */
    public String sayWelcome() {
        // TODO: Return "Welcome to Java Programming"
        return "";
    }

    /**
     * TODO: Return the sum of two integers.
     *
     * @param a first integer
     * @param b second integer
     * @return the sum of a and b
     */
    public int sum(int a, int b) {
        // TODO: implement the sum logic
        return 0;
    }

    public static void main(String[] args) {
        App app = new App();

        // Print the welcome message
        System.out.println(app.sayWelcome());

        // Prompt the user for two numbers
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter the second number: ");
        int num2 = scanner.nextInt();

        // Print their sum
        System.out.println("The sum is: " + app.sum(num1, num2));
    }
}

