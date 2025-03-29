# Comprehensive Java OOP Concepts

## Classes, Objects, and Constructors

### 1. Classes and Objects

**Class Definition:**

```java
/**
 * A class is a blueprint for creating objects
 * - Contains fields (state) and methods (behavior)
 * - Naming convention: PascalCase
 */
public class BankAccount {
    // Instance Variables (State)
    private String accountNumber;  // Field encapsulation
    private double balance;

    // Class Variable (Shared across instances)
    public static final String BANK_NAME = "MyBank";  // Constant

    // Constructor (Special instance initialization method)
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;  // 'this' resolves name conflict
        this.balance = initialBalance;
    }

    // Instance Method (Behavior)
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
        }
    }

    // Accessor Method (Getter)
    public double getBalance() {
        return balance;
    }

    // Static Method (Class-level operation)
    public static void displayBankInfo() {
        System.out.println("Welcome to " + BANK_NAME);
    }
}
```

**Object Instantiation:**

```java
public class Main {
    public static void main(String[] args) {
        // Create object using 'new' operator
        BankAccount acc1 = new BankAccount("ACC123", 1000.0);
        acc1.deposit(500.0);
        System.out.println(acc1.getBalance());  // 1500.0

        // Static member access
        BankAccount.displayBankInfo();  // "Welcome to MyBank"
    }
}
```

---

### 2. Constructors

**Key Constructor Features:**

- Same name as class
- No return type
- Can be overloaded
- Automatically invoked during object creation

**Constructor Types:**

```java
public class Employee {
    private String name;
    private int id;

    // Default Constructor (Compiler provides if no constructor exists)
    public Employee() {
        this.name = "Unknown";
        this.id = 0;
    }

    // Parameterized Constructor
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Copy Constructor
    public Employee(Employee other) {
        this.name = other.name;
        this.id = other.id;
    }

    // Constructor Chaining using 'this()'
    public Employee(String name) {
        this(name, 0);  // Calls parameterized constructor
    }
}
```

---

### 3. Method Overloading

**Rules:**

- Same method name
- Different parameter lists (type, count, or order)
- Return type can vary but isn't sufficient alone

```java
public class MathOperations {

    // Integer addition
    public int add(int a, int b) {
        return a + b;
    }

    // Double addition
    public double add(double a, double b) {
        return a + b;
    }

    // Varargs method
    public int add(int... numbers) {
        int sum = 0;
        for(int num : numbers) {
            sum += num;
        }
        return sum;
    }

    // Different parameter order
    public String concatenate(String a, int b) {
        return a + b;
    }

    public String concatenate(int a, String b) {
        return a + b;
    }
}
```

---

### 4. Object Class & Garbage Collection

**Object Class Methods:**
| Method | Purpose |
|-----------------|------------------------------------------|
| toString() | String representation of object |
| equals() | Content comparison |
| hashCode() | Hash code for hash-based collections |
| getClass() | Returns runtime class of object |
| clone() | Creates copy of object |
| finalize() | Called before garbage collection (deprecated) |

**Garbage Collection:**

```java
public class GCDemo {
    public static void main(String[] args) {
        // Object creation
        Object obj1 = new Object();
        Object obj2 = new Object();

        // Object references
        obj1 = obj2;  // Original obj1 becomes eligible for GC
        obj2 = null;  // obj2 reference removed

        // Explicit garbage collection request (not guaranteed)
        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Garbage collected!");
    }
}
```

---

## Inheritance and Packages

### 1. Inheritance Hierarchy

**Types of Inheritance:**

1. **Single:** One subclass ← One superclass
2. **Multilevel:** Class → Subclass → Sub-subclass
3. **Hierarchical:** Multiple subclasses ← One superclass
4. **Multiple:** Through interfaces only (class implements multiple interfaces)

```java
// Base Class
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

// Single Inheritance
class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}

// Multilevel Inheritance
class Puppy extends Dog {
    void weep() {
        System.out.println("Weeping...");
    }
}

// Hierarchical Inheritance
class Cat extends Animal {
    void meow() {
        System.out.println("Meowing...");
    }
}
```

---

### 2. Super Keyword

**Three Uses:**

1. Access parent class fields
2. Invoke parent class methods
3. Call parent class constructors

```java
class Vehicle {
    int speed = 50;

    Vehicle() {
        System.out.println("Vehicle created");
    }

    void display() {
        System.out.println("Vehicle display");
    }
}

class Car extends Vehicle {
    int speed = 100;

    Car() {
        super();  // Explicit parent constructor call
        System.out.println("Car created");
    }

    void display() {
        System.out.println("Car speed: " + speed);
        System.out.println("Vehicle speed: " + super.speed);
        super.display();
    }
}
```

---

### 3. Method Overriding

**Rules:**

- Same method signature as parent
- Access modifier cannot be more restrictive
- Cannot override final/private/static methods
- Use @Override annotation for safety

```java
class Shape {
    public void draw() {
        System.out.println("Drawing shape");
    }

    public final void resize() {
        System.out.println("Resizing shape");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
        super.draw();  // Call parent implementation
    }

    // Cannot override resize() - final method
}
```

---

### 4. Interfaces

**Key Features:**

- 100% abstract (before Java 8)
- Supports default and static methods
- Multiple implementation allowed
- No instance variables (only constants)

```java
interface Drawable {
    void draw();  // Abstract method

    default void printInfo() {  // Default method
        System.out.println("Drawable object");
    }

    static void displayVersion() {  // Static method
        System.out.println("Drawable v1.0");
    }
}

interface Scalable {
    void scale(double factor);
}

// Implementing multiple interfaces
class Circle implements Drawable, Scalable {
    private double radius;

    @Override
    public void draw() {
        System.out.println("Drawing circle with radius " + radius);
    }

    @Override
    public void scale(double factor) {
        radius *= factor;
    }
}
```

---

### 5. Packages

**Package Structure:**

```
project/
├── src/
│   ├── com/
│   │   └── company/
│   │       ├── utils/
│   │       │   └── MathHelper.java
│   │       └── Main.java
```

**Access Control:**
| Modifier | Class | Package | Subclass | World |
|----------------|-------|---------|----------|-------|
| public | ✓ | ✓ | ✓ | ✓ |
| protected | ✓ | ✓ | ✓ | ✗ |
| default (none) | ✓ | ✓ | ✗ | ✗ |
| private | ✓ | ✗ | ✗ | ✗ |

**Example Package Usage:**

```java
// File: com/company/utils/MathHelper.java
package com.company.utils;

public class MathHelper {
    public static double PI = 3.141592653589793;

    public static int square(int n) {
        return n * n;
    }
}

// File: com/company/Main.java
package com.company;

import com.company.utils.MathHelper;

public class Main {
    public static void main(String[] args) {
        System.out.println("PI value: " + MathHelper.PI);
        System.out.println("Square of 5: " + MathHelper.square(5));
    }
}
```

---

## Best Practices & Common Pitfalls

### Inheritance Guidelines

- Favor composition over inheritance
- Use abstract classes for shared implementation
- Keep inheritance hierarchies shallow
- Avoid fragile base class problem

### Package Conventions

- Use reverse domain notation: `com.company.project.module`
- Keep package contents cohesive
- Avoid circular dependencies
- Use JAR files for distribution

### Method Design Tips

- Limit method parameters (≤ 3)
- Prefer immutable objects
- Use method overloading judiciously
- Document using Javadoc comments

### Common Errors Table

| Error Type             | Example                      | Solution                     |
| ---------------------- | ---------------------------- | ---------------------------- |
| Accidental Overloading | Changing return type only    | Use @Override annotation     |
| Hidden Methods         | static method with same name | Use different method names   |
| Constructor Chaining   | Circular this()/super()      | Ensure proper initialization |
| Package Visibility     | Missing import statements    | Check package declarations   |
