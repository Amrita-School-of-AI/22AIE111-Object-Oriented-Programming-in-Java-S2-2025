package org.example;

public class App {

    /* ==============
       ARITHMETIC
       ============== */
    /**
     * TODO: Return the sum of two integers.
     */
    public int add(int a, int b) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the difference of two integers.
     */
    public int subtract(int a, int b) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the product of two integers.
     */
    public int multiply(int a, int b) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the integer division result (a / b).
     * Assume b != 0.
     */
    public int divide(int a, int b) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the remainder (modulus) when a is divided by b.
     * Assume b != 0.
     */
    public int modulus(int a, int b) {
        // TODO
        return 0;
    }

    /* ==============
       RELATIONAL
       ============== */
    /**
     * TODO: Return true if a > b.
     */
    public boolean greaterThan(int a, int b) {
        // TODO
        return false;
    }

    /**
     * TODO: Return true if a == b.
     */
    public boolean equalTo(int a, int b) {
        // TODO
        return false;
    }

    /**
     * TODO: Return true if a <= b.
     */
    public boolean lessThanOrEqual(int a, int b) {
        // TODO
        return false;
    }

    /* ==============
       LOGICAL
       ============== */
    /**
     * TODO: Return the result of logical AND on x and y.
     */
    public boolean logicalAnd(boolean x, boolean y) {
        // TODO
        return false;
    }

    /**
     * TODO: Return the result of logical OR on x and y.
     */
    public boolean logicalOr(boolean x, boolean y) {
        // TODO
        return false;
    }

    /**
     * TODO: Return the result of logical NOT on x.
     */
    public boolean logicalNot(boolean x) {
        // TODO
        return false;
    }

    /* ==============
       BITWISE
       ============== */
    /**
     * TODO: Return the result of bitwise AND on a and b.
     */
    public int bitwiseAnd(int a, int b) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the result of bitwise OR on a and b.
     */
    public int bitwiseOr(int a, int b) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the result of bitwise XOR on a and b.
     */
    public int bitwiseXor(int a, int b) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the bitwise complement of a.
     */
    public int bitwiseNot(int a) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the result of left-shifting a by shift bits.
     */
    public int leftShift(int a, int shift) {
        // TODO
        return 0;
    }

    /**
     * TODO: Return the result of right-shifting a by shift bits.
     */
    public int rightShift(int a, int shift) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        App app = new App();

        // ARITHMETIC
        System.out.println("Arithmetic Examples:");
        System.out.println("add(10, 3) = " + app.add(10, 3));
        System.out.println("subtract(10, 3) = " + app.subtract(10, 3));
        System.out.println("multiply(10, 3) = " + app.multiply(10, 3));
        System.out.println("divide(10, 3) = " + app.divide(10, 3));
        System.out.println("modulus(10, 3) = " + app.modulus(10, 3));

        // RELATIONAL
        System.out.println("\nRelational Examples:");
        System.out.println("greaterThan(10, 3) = " + app.greaterThan(10, 3));
        System.out.println("equalTo(10, 10) = " + app.equalTo(10, 10));
        System.out.println("lessThanOrEqual(3, 3) = " + app.lessThanOrEqual(3, 3));

        // LOGICAL
        System.out.println("\nLogical Examples:");
        System.out.println("logicalAnd(true, false) = " + app.logicalAnd(true, false));
        System.out.println("logicalOr(true, false) = " + app.logicalOr(true, false));
        System.out.println("logicalNot(true) = " + app.logicalNot(true));

        // BITWISE
        System.out.println("\nBitwise Examples:");
        System.out.println("bitwiseAnd(5, 3) = " + app.bitwiseAnd(5, 3));
        System.out.println("bitwiseOr(5, 3) = " + app.bitwiseOr(5, 3));
        System.out.println("bitwiseXor(5, 3) = " + app.bitwiseXor(5, 3));
        System.out.println("bitwiseNot(5) = " + app.bitwiseNot(5));
        System.out.println("leftShift(5, 1) = " + app.leftShift(5, 1));
        System.out.println("rightShift(5, 1) = " + app.rightShift(5, 1));
    }
}

