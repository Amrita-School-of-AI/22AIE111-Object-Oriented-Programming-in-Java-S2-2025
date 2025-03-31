# Detailed Notes: Java Exception Handling and Threading

## Part 1: Java Exception Handling

### 1. Introduction to Exceptions

- **What is an Exception?** An exception is an event that occurs during the execution of a program that disrupts the normal flow of instructions. It represents an error condition or an unexpected situation.
- **Why Handle Exceptions?** Proper exception handling prevents program crashes and allows the program to recover gracefully or terminate in a controlled manner. It separates error-handling code from the main program logic.

### 2. Exception Hierarchy

Java's exception hierarchy has `Throwable` as the root class. It has two main subclasses:

1.  **`Error`**: Represents serious problems that a reasonable application should not try to catch. These are typically unrecoverable issues related to the JVM environment itself (e.g., `OutOfMemoryError`, `StackOverflowError`). You usually don't handle these.
2.  **`Exception`**: Represents conditions that a reasonable application might want to catch. This is the class you'll typically work with for exception handling.

    - **Checked Exceptions**: Subclasses of `Exception` _excluding_ `RuntimeException` and its subclasses. The compiler _checks_ if these are handled (using `try-catch`) or declared (using `throws`). Examples: `IOException`, `SQLException`, `FileNotFoundException`. These often relate to external resources or operations that might fail.
    - **Unchecked Exceptions (Runtime Exceptions)**: Subclasses of `RuntimeException`. The compiler does _not_ check if these are handled or declared. They often indicate programming errors (e.g., logic errors, improper API use). Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`, `IllegalArgumentException`.

```java
// Example Hierarchy Visualization (Conceptual)
/*
           Throwable
           /       \
        Error    Exception
                    /      \
   IOException    RuntimeException
   SQLException   /        |       \
   ...        NullPointerException  ArithmeticException  ArrayIndexOutOfBoundsException
                 ...
*/
```

### 3. Exception Handling Keywords

Java provides keywords to manage exceptions:

1.  **`try`**: The `try` block encloses the code that might potentially throw an exception.
2.  **`catch`**: The `catch` block follows a `try` block and handles a specific type of exception. You can have multiple `catch` blocks to handle different exception types. The first matching `catch` block is executed.
3.  **`finally`**: The `finally` block follows `try` or `try-catch`. This block _always_ executes, whether an exception was thrown or not, and whether a caught exception was handled or not. Its primary use is for cleanup code (e.g., closing files, releasing network connections).
    - **Important `finally` characteristics:**
      - It executes even if a `return`, `break`, or `continue` statement is encountered in the `try` or `catch` blocks.
      - It can exist without a `catch` block (`try-finally`).
      - It will _not_ execute if the JVM exits abruptly within the `try` or `catch` block (e.g., via `System.exit()`).
      - It can contain a `return` statement, but this is generally discouraged as it can suppress exceptions thrown from the `try` or `catch` blocks and make debugging harder.
4.  **`throw`**: Used to explicitly throw an exception object (either a predefined one or a custom one).
5.  **`throws`**: Used in a method signature to declare the types of _checked_ exceptions that the method might throw but does not handle internally. Callers of the method must either handle these exceptions using `try-catch` or declare them using `throws` again.

_(Note: `final` is a keyword in Java used for constants, final methods, and final classes, but it is NOT used for exception handling.)_

### 4. Code Examples: Exception Handling

**Example 1: Basic `try-catch` for `ArithmeticException`**

```java
public class ExceptionDemo1 {
    public static void main(String[] args) {
        try {
            int a = 10;
            int b = 0;
            System.out.println("Attempting division...");
            int result = a / b; // This line throws ArithmeticException
            System.out.println("Result: " + result); // This line won't execute
        } catch (ArithmeticException e) {
            System.err.println("Error: Cannot divide by zero.");
            // e.printStackTrace(); // Often useful for debugging
        }
        System.out.println("Program continues after handling exception.");
    }
}
// Output:
// Attempting division...
// Error: Cannot divide by zero.
// Program continues after handling exception.
```

**Example 2: `try-catch-finally`**

```java
import java.io.*;

public class FinallyDemo {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            System.out.println("Entering try block.");
            writer = new PrintWriter(new FileWriter("output.txt"));
            writer.println("Writing to file.");
            // Simulate an error
            // int result = 10 / 0; // Uncomment to see finally execute after exception
            System.out.println("Leaving try block normally.");
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Caught ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("Entering finally block.");
            if (writer != null) {
                System.out.println("Closing PrintWriter.");
                writer.close(); // Essential cleanup
            } else {
                System.out.println("PrintWriter was not opened.");
            }
            System.out.println("Leaving finally block.");
        }
        System.out.println("Program finished.");
    }
}
// Possible Output (if no exception):
// Entering try block.
// Leaving try block normally.
// Entering finally block.
// Closing PrintWriter.
// Leaving finally block.
// Program finished.

// Possible Output (if ArithmeticException occurs):
// Entering try block.
// Caught ArithmeticException: / by zero
// Entering finally block.
// Closing PrintWriter.
// Leaving finally block.
// Program finished.
```

**Example 3: `try-finally` (No `catch`)**

```java
public class TryFinallyDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Inside try block.");
            // Code that might throw an *unchecked* exception or needs cleanup regardless
            // If a checked exception could occur here, the method would need 'throws'
        } finally {
            System.out.println("Inside finally block - always executes for cleanup.");
        }
        System.out.println("After try-finally.");
    }
}
// Output:
// Inside try block.
// Inside finally block - always executes for cleanup.
// After try-finally.
```

**Example 4: `throws` Keyword (Declaring Checked Exceptions)**

```java
import java.io.IOException;

public class ThrowsDemo {

    // Method declares it might throw IOException
    public static void readFile(String fileName) throws IOException {
        System.out.println("Attempting to read file: " + fileName);
        // Simulating file reading that could fail
        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("File name is invalid.");
        }
        System.out.println("File read successfully (simulated).");
    }

    public static void main(String[] args) {
        try {
            readFile("myFile.txt");
            readFile(null); // This call will throw the exception
        } catch (IOException e) {
            System.err.println("Caught in main: " + e.getMessage());
        }
    }
}
// Output:
// Attempting to read file: myFile.txt
// File read successfully (simulated).
// Attempting to read file: null
// Caught in main: File name is invalid.
```

**Example 5: Handling `InputMismatchException`**

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputMismatchDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        try {
            System.out.print("Enter an integer: ");
            number = scanner.nextInt(); // Potential InputMismatchException
            System.out.println("You entered: " + number);
        } catch (InputMismatchException e) {
            System.err.println("Error: You entered bad data. Please enter an integer.");
            // Consume the invalid input
            scanner.next();
        } finally {
            scanner.close(); // Good practice to close scanner
            System.out.println("Scanner closed.");
        }
    }
}
```

---

## Part 2: Java Threading

### 1. Introduction to Threads and Multithreading

- **What is a Thread?** A thread is the smallest unit of execution within a process. A Java program runs in a process, and by default, it has at least one thread, usually called the _main_ thread.
- **What is Multithreading?** Multithreading is the ability of a program to execute multiple threads _concurrently_. This means threads can run seemingly simultaneously, sharing the process's resources like memory space but having their own execution path (program counter, stack).
- **Benefits:** Improved responsiveness (UI remains active during background tasks), potential performance gains on multi-core processors (true parallelism), efficient resource sharing.
- **Challenges:** Complexity in design and debugging, potential for concurrency issues like race conditions and deadlocks if shared resources aren't managed carefully (requires synchronization).

### 2. Thread Lifecycle States

A Java thread can be in one of the following states:

1.  **`NEW`**: The thread object has been created, but the `start()` method has not yet been called. The thread is not yet alive.
2.  **`RUNNABLE`**: The thread is eligible to be run by the JVM's thread scheduler. This state includes both threads that are actually running and those that are ready to run but waiting for CPU time.
3.  **`BLOCKED`**: The thread is waiting to acquire a monitor lock to enter a `synchronized` block/method or after calling `Object.wait()`.
4.  **`WAITING`**: The thread is waiting indefinitely for another thread to perform a particular action. This happens after calls like `Object.wait()` (without timeout), `Thread.join()` (without timeout), or `LockSupport.park()`.
5.  **`TIMED_WAITING`**: The thread is waiting for a specific period. This occurs after calls like `Thread.sleep(long millis)`, `Object.wait(long timeout)`, `Thread.join(long millis)`, `LockSupport.parkNanos()`, `LockSupport.parkUntil()`.
6.  **`TERMINATED`**: The thread has completed its execution (its `run()` method has finished) or has otherwise terminated. It's dead and cannot be restarted.

### 3. Creating and Starting Threads

There are two primary ways to create a thread in Java:

**Method 1: Extending the `Thread` Class**

1.  Create a class that extends `java.lang.Thread`.
2.  Override the `run()` method. This method contains the code that will be executed by the new thread.
3.  Create an instance of your subclass.
4.  Call the `start()` method on the instance. **Crucially, call `start()`, not `run()`.**

**Example 6: Extending `Thread`**

```java
class MyThread extends Thread {
    private String threadName;

    MyThread(String name) {
        this.threadName = name;
        System.out.println("Creating " +  threadName );
    }

    @Override
    public void run() { // This code runs in the new thread when start() is called
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a short time
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }
}

public class ThreadExtensionDemo {
   public static void main(String args[]) {
      MyThread t1 = new MyThread("Thread-1");
      MyThread t2 = new MyThread("Thread-2");

      System.out.println("Starting threads...");
      t1.start(); // Starts t1, JVM calls t1.run() in a new thread
      t2.start(); // Starts t2, JVM calls t2.run() in a new thread

      // Attempting to start the same thread twice will cause IllegalThreadStateException
      try {
          // t1.start(); // If uncommented, this would throw the exception at runtime
      } catch (IllegalThreadStateException e) {
          System.err.println("Error: Cannot start the same thread twice! " + e.getMessage());
      }

      // Calling run() directly executes in the current thread
      System.out.println("\n--- Calling run() directly ---");
      MyThread t3 = new MyThread("Thread-3 (via run)");
      t3.run(); // Executes run() in the *main* thread, NOT a new thread.
      System.out.println("--- Finished calling run() directly ---");

      System.out.println("Main thread finished.");
   }
}
```

**Method 2: Implementing the `Runnable` Interface**

1.  Create a class that implements the `java.lang.Runnable` interface.
2.  Implement the `run()` method.
3.  Create an instance of your class (the `Runnable` object).
4.  Create an instance of the `Thread` class, passing your `Runnable` object to the `Thread` constructor.
5.  Call the `start()` method on the `Thread` object.

- **Why `Runnable` is often preferred:** Java does not support multiple class inheritance. Implementing `Runnable` allows your class to extend another class if needed. It also promotes better object-oriented design (separating the task `Runnable` from the execution mechanism `Thread`).

**Example 7: Implementing `Runnable`**

```java
class MyRunnable implements Runnable {
    private String taskName;

    MyRunnable(String name) {
        this.taskName = name;
        System.out.println("Creating task: " + taskName);
    }

    @Override
    public void run() { // This code runs in the new thread when start() is called on the Thread object
        System.out.println("Running task: " + taskName);
        try {
            for(int i = 0; i < 3; i++) {
                System.out.println("Task: " + taskName + ", Step: " + i);
                Thread.sleep(60);
            }
        } catch (InterruptedException e) {
            System.out.println("Task " + taskName + " interrupted.");
        }
        System.out.println("Task " + taskName + " complete.");
    }
}

public class RunnableDemo {
   public static void main(String args[]) {
      MyRunnable task1 = new MyRunnable("Task-A");
      MyRunnable task2 = new MyRunnable("Task-B");

      // Correct way: Create Thread objects from Runnable tasks
      Thread thread1 = new Thread(task1);
      Thread thread2 = new Thread(task2);

      System.out.println("Starting threads via Runnable...");
      thread1.start(); // Starts thread1, JVM calls task1.run() in a new thread
      thread2.start(); // Starts thread2, JVM calls task2.run() in a new thread

      // Calling run() directly on the Runnable object executes in the current thread
      System.out.println("\n--- Calling run() directly on Runnable ---");
      MyRunnable task3 = new MyRunnable("Task-C (via run)");
      task3.run(); // Executes run() in the *main* thread, NOT a new thread.
      System.out.println("--- Finished calling run() directly on Runnable ---");

      System.out.println("Main thread exiting.");
   }
}
```

### 4. `start()` vs `run()` (Crucial Distinction)

- **`start()`**:
  - Creates a new thread of execution.
  - Places the thread in the `RUNNABLE` state.
  - The Java Virtual Machine (JVM) calls the thread's `run()` method when the thread scheduler allocates CPU time to it.
  - Returns immediately; the caller (e.g., the main thread) continues execution concurrently with the new thread.
  - Can only be called _once_ per `Thread` object. Calling it again throws `IllegalThreadStateException`.
- **`run()`**:
  - Just a normal method call.
  - Executes the code inside the `run()` method within the _current_ thread (the thread that made the call).
  - Does _not_ create a new thread.
  - The caller waits for the `run()` method to complete before continuing (unless `run()` itself starts other threads).
  - Can be called multiple times, just like any other method.

### 5. Thread Priority

- Threads can be assigned a priority (an integer between `Thread.MIN_PRIORITY` (1) and `Thread.MAX_PRIORITY` (10)). The default is `Thread.NORM_PRIORITY` (5).
- Methods: `setPriority(int newPriority)`, `getPriority()`.
- **Purpose:** Thread priority acts as a _hint_ to the thread scheduler. The scheduler _may_ favor running higher-priority threads over lower-priority ones when choosing which `RUNNABLE` thread to execute next.
- **Important Caveat:** Thread scheduling behavior is highly dependent on the underlying Operating System and the specific JVM implementation. **There is no guarantee that higher-priority threads will always run before or more often than lower-priority threads.** Relying heavily on priorities for program correctness is generally discouraged.

**Example 8: Thread Priority**

```java
class PriorityWorker extends Thread {
    public PriorityWorker(String name) {
        super(name);
    }
    public void run() {
        for (int i=0; i<5; i++) {
             System.out.println(Thread.currentThread().getName() +
                 " (Priority " + Thread.currentThread().getPriority() + ") running - count " + i);
             // Yield to potentially allow other threads to run
             Thread.yield();
        }
    }
}

public class PriorityDemo {
    public static void main(String[] args) {
        PriorityWorker t1 = new PriorityWorker("LowPriorityThread");
        PriorityWorker t2 = new PriorityWorker("HighPriorityThread");
        PriorityWorker t3 = new PriorityWorker("NormalPriorityThread");

        t1.setPriority(Thread.MIN_PRIORITY); // Priority 1
        t2.setPriority(Thread.MAX_PRIORITY); // Priority 10
        // t3 has default priority (NORM_PRIORITY = 5)

        System.out.println("Starting threads with different priorities...");
        // Start order might influence initial execution slightly, but priority is the main factor (theoretically)
        t1.start();
        t3.start();
        t2.start();

        System.out.println("Main thread finished initiating threads.");
        // Note: The output order is NOT guaranteed due to scheduling variations.
        // HighPriorityThread *might* run more initially, but it's not certain.
    }
}
```

### 6. Thread Synchronization

- **Problem:** When multiple threads access and modify shared mutable data concurrently, it can lead to unexpected results and inconsistent data states. This is known as a **race condition**.
- **Solution:** Synchronization is a mechanism to control access to shared resources. It ensures that only one thread can execute a critical section (code that accesses shared resources) at a time.
- **Primary Purpose:** To prevent race conditions and ensure data consistency (integrity) in multithreaded applications.
- **Mechanisms:**
  - **`synchronized` Methods:** Adding the `synchronized` keyword to a method declaration locks the object (`this` for instance methods, the `Class` object for static methods) before execution. Only one thread can execute _any_ synchronized instance method on the _same object_ at a time.
  - **`synchronized` Blocks:** Allows finer-grained control. You specify an object to lock on. Only one thread can execute a block synchronized on the _same object_ at a time.
    ```java
    synchronized (objectToLockOn) {
        // Access shared resources safely here
    }
    ```
  - Other mechanisms exist (e.g., `java.util.concurrent.locks.Lock` interface, atomic variables).

**Example 9: Basic Synchronization**

```java
class Counter {
    private int count = 0;

    // Without synchronization, multiple threads calling this might lead to incorrect final count
    // public void increment() { count++; }

    // Synchronized method: Ensures atomic increment
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}

public class SynchronizationDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter sharedCounter = new Counter();

        CounterThread threadA = new CounterThread(sharedCounter);
        CounterThread threadB = new CounterThread(sharedCounter);

        threadA.start();
        threadB.start();

        // Wait for both threads to finish
        threadA.join();
        threadB.join();

        System.out.println("Final count: " + sharedCounter.getCount()); // Should be 20000
    }
}
// Output (with synchronized):
// Final count: 20000
// Output (if increment() was not synchronized, likely < 20000 due to race conditions)
```

---

## Part 3: Interfaces and Abstract Classes

These concepts are fundamental to Java object-oriented programming and often interact with topics like threading and callbacks.

### 1. Interface

- An interface is a completely abstract type. It defines a contract of methods that implementing classes must provide.
- **Characteristics:**
  - Cannot be instantiated directly.
  - Methods are implicitly `public` and `abstract` (before Java 8).
  - From Java 8 onwards, interfaces can have `default` methods (with implementation) and `static` methods (with implementation).
  - Variables declared in an interface are implicitly `public`, `static`, and `final` (constants).
  - A class can `implement` multiple interfaces (achieving a form of multiple inheritance of _type_).
  - An interface can `extend` multiple other interfaces.
  - Cannot contain constructors.

**Example 10: Interface Implementation**

```java
// Define an interface
interface Greeter {
    void sayHello(String name); // Method signature (implicitly public abstract)
}

// Implement the interface
class SimpleGreeter implements Greeter {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }
}

// Example usage
public class InterfaceExample {
    public static void main(String[] args) {
        SimpleGreeter sg = new SimpleGreeter();
        sg.sayHello("World"); // Output: Hello, World!

        Greeter gRef = sg; // Can hold reference using interface type
        gRef.sayHello("Alice"); // Output: Hello, Alice!
    }
}
```

### 2. Abstract Class

- A class declared with the `abstract` keyword. It is designed to be subclassed.
- **Characteristics:**
  - Cannot be instantiated directly.
  - _Can_ contain `abstract` methods (methods without implementation, declared with `abstract` keyword). Subclasses _must_ implement these unless they are also abstract.
  - _Can_ contain concrete (regular) methods with implementation.
  - Can contain instance variables (state).
  - Can contain constructors. These are typically called via `super()` from subclass constructors.
  - A class can `extend` only _one_ abstract (or concrete) class (single class inheritance).

**Example 11: Abstract Class**

```java
abstract class Shape {
    private String color;

    // Constructor
    public Shape(String color) {
        this.color = color;
        System.out.println("Shape constructor called.");
    }

    // Concrete method
    public String getColor() {
        return color;
    }

    // Abstract method - must be implemented by non-abstract subclasses
    public abstract double getArea();
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color); // Call abstract class constructor
        this.radius = radius;
        System.out.println("Circle constructor called.");
    }

    @Override // Implement the abstract method
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

public class AbstractClassDemo {
    public static void main(String[] args) {
        // Shape s = new Shape("Red"); // Error: Cannot instantiate abstract class
        Circle c = new Circle("Blue", 5.0);
        System.out.println("Color: " + c.getColor());
        System.out.println("Area: " + c.getArea());
    }
}
// Output:
// Shape constructor called.
// Circle constructor called.
// Color: Blue
// Area: 78.53981633974483
```

### 3. Key Differences Summary

| Feature           | Abstract Class                       | Interface                                        |
| :---------------- | :----------------------------------- | :----------------------------------------------- |
| **Instantiation** | Cannot be instantiated               | Cannot be instantiated                           |
| **Methods**       | Abstract & Concrete methods          | Abstract, Default, Static methods                |
| **Variables**     | Instance & Static variables          | Only `public static final` variables             |
| **Constructors**  | Can have constructors                | Cannot have constructors                         |
| **Inheritance**   | Class extends **one** abstract class | Class implements **multiple** interfaces         |
| **Keyword**       | `extends`                            | `implements` (`extends` for interface-interface) |

### 4. Inheritance with Interfaces and Classes

- A class can implement an interface and extend a class simultaneously.
- When a subclass overrides a method from its superclass, it provides a new implementation. The `super` keyword can be used within the subclass method to call the superclass's version of the method.

**Example 12: Interface, Inheritance, `super`**

```java
interface Calculator {
    void calculate(int value);
}

class Square implements Calculator {
    int result; // Instance variable

    @Override
    public void calculate(int value) {
        result = value * value;
        System.out.print("Square: " + result + " ");
    }
}

// Cube extends Square AND implicitly implements Calculator (inherited)
class Cube extends Square {
    // 'result' variable is inherited from Square

    @Override // Overrides calculate method from Square
    public void calculate(int value) {
        // Call the superclass (Square) version of calculate first
        super.calculate(value); // This will print "Square: <value*value> "

        // Now perform the Cube calculation and print
        result = value * value * value; // Overwrites inherited 'result'
        System.out.print("Cube: " + result + " ");
    }
}

public class InheritanceInterfaceDemo {
    public static void main(String[] args) {
        Calculator obj = new Cube(); // Polymorphism: Interface ref holding subclass object
        obj.calculate(3); // Output: Square: 9 Cube: 27
        System.out.println(); // Newline for clarity
        obj.calculate(4); // Output: Square: 16 Cube: 64
    }
}
```

---

## Part 4: Practical Code Examples

### Example: Define and Implement a Simple `Number` Interface

```java
import java.lang.Math; // Import Math class

// Define the interface 'Number'
interface Number {
    // Declare the method signature for finding a square
    double findSqr(double n);
}

// Define class 'A' that implements the interface 'Number'
class A implements Number {

    // Implement the method declared in the Number interface
    @Override
    public double findSqr(double n) {
        // Use the Math class to find the square
        return Math.pow(n, 2);
    }

    // Example main method to test
    public static void main(String[] args) {
        A objA = new A();
        double number = 7.0;
        double square = objA.findSqr(number);
        System.out.println("The square of " + number + " is " + square);

        Number numRef = objA; // Use interface reference
        System.out.println("The square of 10.0 is " + numRef.findSqr(10.0));
    }
}
// Expected Output:
// The square of 7.0 is 49.0
// The square of 10.0 is 100.0
```

### Example: Initialize Array, Handle `InputMismatchException`, Calculate Sum

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArraySumWithExceptionHandling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 3; // Define a fixed size for the example array
        int[] data = new int[size];
        int sum = 0;
        boolean inputError = false;

        System.out.println("Enter " + size + " integer values:");

        for (int i = 0; i < size; i++) {
            try {
                System.out.print("Enter element " + (i + 1) + ": ");
                data[i] = scanner.nextInt(); // Potential InputMismatchException
            } catch (InputMismatchException e) {
                System.out.println("You entered bad data. Please enter only integers.");
                inputError = true;
                // Consume the invalid input to prevent infinite loop if scanner.nextInt() failed
                scanner.next();
                // Break the loop on error
                 break;
            }
        }

        // Close the scanner after input loop is done
        scanner.close();

        // Calculate sum only if there was no input error
        if (!inputError) {
            for (int value : data) {
                sum += value;
            }
            System.out.println("Array initialization successful.");
            System.out.println("Total sum of the array elements: " + sum);
        } else {
            System.out.println("Array initialization failed due to bad input. Cannot calculate sum.");
        }

         System.out.println("Program finished.");
    }
}
// Example Run 1 (Successful):
// Enter 3 integer values:
// Enter element 1: 10
// Enter element 2: 20
// Enter element 3: 30
// Array initialization successful.
// Total sum of the array elements: 60
// Program finished.

// Example Run 2 (Input Error):
// Enter 3 integer values:
// Enter element 1: 10
// Enter element 2: abc
// You entered bad data. Please enter only integers.
// Array initialization failed due to bad input. Cannot calculate sum.
// Program finished.
```
