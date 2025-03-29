# Exception Handling and Threading in Java

## Exception Handling

### 1. Introduction to Exception Handling

- **Exception**: An event that disrupts normal program flow during execution
- **Purpose**:
  - Prevent application crashes
  - Separate error-handling code from main logic
  - Provide meaningful error messages
- **Key Concepts**:
  - Try: Block containing risky code
  - Catch: Handles specific exceptions
  - Finally: Cleanup code (always executes)
  - Throw: Explicitly generate exception
  - Throws: Declares possible exceptions

### 2. Exception Hierarchy

```java
Throwable (root)
├── Error (unchecked - JVM serious failures)
│    ├── OutOfMemoryError
│    └── StackOverflowError
│
└── Exception (checked unless Runtime)
     ├── IOException
     ├── SQLException
     └── RuntimeException (unchecked)
          ├── NullPointerException
          └── ArrayIndexOutOfBoundsException
```

### 3. Exception Handling Constructs

**Basic Try-Catch-Finally**:

```java
try {
    FileReader file = new FileReader("test.txt");
    // Code that might throw IOException
}
catch (FileNotFoundException e) {
    System.err.println("File not found: " + e.getMessage());
}
catch (IOException e) {
    System.err.println("I/O Error: " + e.getMessage());
}
finally {
    // Close resources here
    if(file != null) file.close();
}
```

**Multiple Catch Blocks**:

- Order matters: Specific → General
- Java 7+ Multi-catch:

```java
catch (IOException | SQLException e) {
    // Handle multiple exceptions
}
```

**Try-With-Resources (Java 7+)**:

```java
try (BufferedReader br = new BufferedReader(new FileReader(path))) {
    return br.readLine();
}
// Auto-closes resources implementing AutoCloseable
```

### 4. Custom Exceptions

```java
public class InsufficientFundsException extends Exception {
    private double deficit;

    public InsufficientFundsException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }

    public double getDeficit() {
        return deficit;
    }
}

// Usage
throw new InsufficientFundsException("Not enough balance", 500.0);
```

### 5. Best Practices

- Use specific exception types
- Document exceptions with Javadoc @throws
- Clean up resources in finally blocks
- Avoid empty catch blocks
- Throw exceptions at appropriate abstraction levels

---

## Threading and Multithreading

### 1. Thread Fundamentals

- **Thread**: Lightweight sub-process, basic unit of CPU utilization
- **Key Benefits**:
  - Improved responsiveness
  - Better resource utilization
  - Simplified modeling of concurrent tasks

### 2. Thread Creation Methods

**Method 1: Extend Thread Class**

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + this.getName());
    }
}

// Usage
MyThread t1 = new MyThread();
t1.start();
```

**Method 2: Implement Runnable Interface**

```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread running");
    }
}

// Usage
Thread t2 = new Thread(new MyRunnable());
t2.start();
```

**Java 8+ Lambda Syntax**:

```java
Thread t3 = new Thread(() -> {
    System.out.println("Lambda thread running");
});
t3.start();
```

### 3. Thread Lifecycle States

1. **New**: Created but not started
2. **Runnable**: Ready to run (in thread pool)
3. **Running**: Actively executing
4. **Blocked/Waiting**: Waiting for monitor lock
5. **Timed Waiting**: Sleeping or waiting with timeout
6. **Terminated**: Completed execution

![Thread Lifecycle Diagram](https://www.baeldung.com/wp-content/uploads/2018/02/Life_cycle_of_a_Thread_in_Java.jpg)

### 4. Thread Control Methods

| Method             | Description                           |
| ------------------ | ------------------------------------- |
| start()            | Begins thread execution               |
| sleep(long millis) | Suspends execution for specified time |
| join()             | Waits for thread to die               |
| interrupt()        | Interrupts waiting/sleeping thread    |
| setPriority(int)   | Changes thread priority (1-10)        |

### 5. Multithreading Concepts

**Shared Resource Example**:

```java
class Counter {
    private int count = 0;

    public synchronized void increment() {  // Synchronized method
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

**Common Issues**:

- **Race Conditions**: Uncontrolled access to shared resources
- **Deadlocks**: Circular waiting for resources
- **Starvation**: Threads not getting CPU time
- **Memory Consistency**: Visibility issues between threads

**Synchronization Techniques**:

1. Synchronized methods/blocks
2. Volatile variables
3. Atomic classes (AtomicInteger, etc.)
4. Lock objects (ReentrantLock)

### 6. Executor Framework (Java 5+)

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

executor.execute(() -> {
    // Task to execute
});

executor.shutdown();  // Important to prevent resource leaks
```

### 7. Best Practices

- Prefer Runnable over Thread extension
- Use thread pools instead of creating new threads
- Avoid thread starvation with proper synchronization
- Use higher-level concurrency utilities when possible
- Always handle InterruptedException properly

---

## Common Exception Handling Patterns

### 1. Resource Management

```java
try (Connection conn = DriverManager.getConnection(url);
     PreparedStatement stmt = conn.prepareStatement(sql)) {

    // Use resources
}
catch (SQLException e) {
    // Handle database errors
}
```

### 2. Exception Translation

```java
try {
    // Low-level code
}
catch (SpecificException e) {
    throw new HigherLevelException("Context message", e);
}
```

### 3. Retry Logic

```java
int retries = 3;
while(retries > 0) {
    try {
        // Operation that might fail
        break;
    }
    catch (TemporaryException e) {
        if(--retries == 0) throw new PermanentFailure(e);
        Thread.sleep(1000);
    }
}
```

---

## Thread Safety Levels

| Safety Level         | Description                           | Example           |
| -------------------- | ------------------------------------- | ----------------- |
| Immutable            | Object cannot change after creation   | String, Integer   |
| Thread-confined      | Only accessed by one thread           | Local variables   |
| Synchronized         | Access controlled via locking         | Vector, Hashtable |
| Concurrent           | Lock-free thread-safe implementations | ConcurrentHashMap |
| Unconditionally safe | Safe for all access patterns          | Atomic classes    |
