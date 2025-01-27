# Compiling and Running Java Applications

These notes cover various methods of compiling and running Java applications:

1. **Command Line (javac & java)**
2. **Packaging into a JAR from the Command Line**
3. **Using Gradle**  
   - Building & Running  
   - Creating a JAR (and other archive formats like ZIP/TAR)  

---

## 1. Command Line Compilation and Running

### 1.1 Single Java File

If you have a single Java file named `Main.java` with a `public static void main(String[] args)` method:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from Main!");
    }
}
```

**Compile**:

```bash
javac Main.java
```

This creates a `Main.class` file in the same directory.

**Run**:

```bash
java Main
```

> **Note**: You omit the `.class` extension. Java automatically looks for the `Main` class.

### 1.2 Multiple Java Files

When your program spans multiple files, say `Main.java`, `Helper.java`, and `AnotherHelper.java`:

1. Ensure all files are in the **same directory** (if **no packages** are used).
2. Compile:

   ```bash
   javac *.java
   ```
   This command compiles all `.java` files in the directory.

3. Run the main class:

   ```bash
   java Main
   ```

If your classes are in **packages**, ensure they’re placed in the proper directory structure matching those packages. For example, if `Main.java` declares `package com.example.myapp;`, the file should be in `com/example/myapp/`.

---

## 2. Packaging into a JAR from the Command Line

### 2.1 Creating a JAR

A **JAR** (Java ARchive) is essentially a ZIP file containing your compiled `.class` files and a special directory `META-INF` with a `MANIFEST.MF` file. If you want an **executable** JAR (one you can run with `java -jar`), you need a `Main-Class` entry in your manifest.

1. **Compile** your classes:

   ```bash
   javac Main.java Helper.java AnotherHelper.java
   ```
2. **Create a manifest** (`MANIFEST.MF`) with the following content (e.g.):

   ```
   Main-Class: Main
   ```

   - This tells the JVM which class contains the `main` method when using `java -jar`.

3. **Build the JAR**:

   ```bash
   jar cfm MyApp.jar MANIFEST.MF *.class
   ```
   - `c` = create a new JAR  
   - `f` = specify the file name  
   - `m` = include the manifest file

### 2.2 Running the JAR

```bash
java -jar MyApp.jar
```

If everything is correct, it will run the `public static void main(String[] args)` method in the `Main` class.

---

## 3. Using Gradle

Gradle is a build automation tool that can manage your compilation, testing, and distribution tasks. It automatically handles **classpaths** and **dependencies**, so you usually don’t need to manually specify them on the command line.

### 3.1 Typical Project Structure

```
MyProject/
├── build.gradle
├── settings.gradle
└── src
    └── main
        └── java
            └── org
                └── example
                    └── App.java
```

In `App.java`, you might have:

```java
package org.example;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello from Gradle!");
    }
}
```

### 3.2 Basic `build.gradle` File

A minimal Gradle configuration for a Java project might look like:

```groovy
plugins {
    id 'java'
    // id 'application' // We'll talk about this plugin shortly
}

group 'org.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    // Your library dependencies here
    // For example: implementation 'com.google.guava:guava:31.1-jre'
}
```

#### 3.2.1 Building

From the root project directory (where `build.gradle` is located), run:

```bash
gradle build
```

- This compiles your classes to `build/classes/java/main/`.
- It produces a **JAR** (usually in `build/libs/`) without a `Main-Class` manifest attribute by default (i.e., it’s not “executable” yet).
- It also runs tests if you have any in `src/test/java/`.

#### 3.2.2 Running

By default, the **Java plugin** doesn’t define a `run` task. You either:
- Use **gradle’s `application` plugin** to enable `gradle run`, **or**
- Manually run `java` with the correct classpath.

##### Option A: Without `application` plugin

If you only have the Java plugin, to run manually:

```bash
java -cp build/classes/java/main org.example.App
```

Here, `build/classes/java/main` is the folder containing your `.class` files, and `org.example.App` is the fully qualified class name (package + class).

##### Option B: With the `application` plugin

Add the **application** plugin and specify the main class in your `build.gradle`:

```groovy
plugins {
    id 'java'
    id 'application'
}

application {
    mainClass = 'org.example.App'
}
```

Now you can use:

```bash
gradle run
```

Gradle automatically sets up the classpath, locates the main class, and runs it.

---

## 4. Packaging With Gradle

### 4.1 Building an Executable JAR

With the `application` plugin applied:

```groovy
plugins {
    id 'java'
    id 'application'
}

application {
    mainClass = 'org.example.App'
}
```

When you run:

```bash
gradle build
```

Gradle will generate a JAR in `build/libs/`. By default:
- The JAR **still** may not have a main manifest attribute.  
- However, you can configure the JAR task to include one, or Gradle can generate start scripts via `installDist` or `distZip`.

#### Option: Customize the JAR manifest

```groovy
jar {
    manifest {
        attributes 'Main-Class': application.mainClass
    }
}
```

This ensures `build/libs/yourproject.jar` has `Main-Class: org.example.App` in its manifest. Then you can do:

```bash
java -jar build/libs/yourproject.jar
```

### 4.2 Distributions (ZIP/TAR)

With the **application** plugin, Gradle can package your application into a ZIP or TAR archive with everything needed to run:

```bash
gradle distZip
gradle distTar
```

This creates archives (in `build/distributions/`) containing:
- A **`lib`** folder (your JAR + dependencies).
- A **`bin`** folder with startup scripts (`.bat` for Windows, a shell script for Unix) that automatically set the classpath and run your app.

**Example usage**:

1. Run `gradle distZip`.
2. Look for `yourproject-1.0-SNAPSHOT.zip` in `build/distributions/`.
3. Unzip it. Inside, you’ll see:
   ```
   yourproject-1.0-SNAPSHOT/
   ├── bin/
   │   ├── yourproject
   │   └── yourproject.bat
   └── lib/
       ├── yourproject.jar
       └── other-dependencies.jar
   ```
4. To run on Unix-like systems:
   ```bash
   cd yourproject-1.0-SNAPSHOT
   ./bin/yourproject
   ```
   On Windows:
   ```powershell
   .\bin\yourproject.bat
   ```

### 4.3 Why Use ZIP or TAR?

- **Easy Distribution**: You can distribute a single archive containing everything needed to run your app.
- **No Need for Manual Classpath**: The generated scripts set up the classpath automatically.
- **Consistency**: The same approach works on different operating systems.

---

## 5. Summary & Best Practices

1. **Command Line** (javac/java):
   - Great for **small** demos or quick scripts.
   - Must manually handle classpaths and packaging if you have dependencies.

2. **Manual JAR**:
   - Good to understand how Java packaging works under the hood.
   - Must provide a `Main-Class` in `MANIFEST.MF` to make a JAR executable.

3. **Gradle**:
   - **Recommended** for larger or more complex projects.  
   - Handles dependencies and classpaths automatically.  
   - `application` plugin provides easy `gradle run` and distribution packaging (`distZip` / `distTar`).  
   - Custom JAR manifests and distribution archives are straightforward to configure.

4. **ZIP/TAR vs JAR** in Gradle:
   - **JAR**: The standard artifact for Java class libraries or smaller applications.  
   - **ZIP/TAR**: Often used for distributing a **complete** runnable package (including scripts and dependencies).

You can choose the method that best fits your project’s size, complexity, and distribution needs.

# Java Fundamentals: Comprehensive Notes

These notes cover the core concepts of Java that form the basis for writing simple to moderately complex programs. By studying these topics, you’ll gain an understanding of Java’s syntax, data types, variables, operators, control statements, functions, and how references work.

---
## Table of Contents

1. [Introduction to Java](#introduction-to-java)  
2. [Hello World Program](#hello-world-program)  
3. [Basic Program Syntax](#basic-program-syntax)  
4. [Data Types](#data-types)  
5. [Variables](#variables)  
6. [Operators](#operators)  
7. [Control Statements](#control-statements)  
8. [Functions: Value vs. Reference Types](#functions-value-vs-reference-types)  
9. [Concept of References in Java](#concept-of-references-in-java)  
10. [Additional Topics & Resources](#additional-topics--resources)

---

## 1. Introduction to Java

**Java** is a general-purpose, object-oriented programming language known for its:
- **Platform independence**: Write once, run anywhere (WORA).  
- **Robust standard libraries**.  
- **Strong typing** and **automatic memory management** (Garbage Collection).

### 1.1 Installing Java
- Download and install the [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/).  
- Ensure the `java` and `javac` commands are accessible on your system path.

### 1.2 Writing and Running Java Programs
1. **Write code** in a file named `YourClassName.java`.
2. **Compile** with `javac YourClassName.java` → generates `YourClassName.class`.
3. **Run** with `java YourClassName`.

---

## 2. Hello World Program

The “Hello World” program is traditionally the first program we write in a new language. It demonstrates fundamental structure:

**Example: `HelloWorld.java`**
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```
### Explanation
- `public class HelloWorld`  
  Defines a class named `HelloWorld` that is accessible to everyone (public).
- `public static void main(String[] args)`  
  The **entry point** for any Java application. The JVM calls this method first.
- `System.out.println(...)`  
  Prints text to the console.

Compile and run:
```bash
javac HelloWorld.java
java HelloWorld
```

---

## 3. Basic Program Syntax

Every Java program at minimum requires:
1. **Class Definition**: `class ClassName { ... }`
2. **Main Method**: `public static void main(String[] args) { ... }`

### Typical Structure
```java
public class BasicSyntax {
    // Fields/Variables
    
    // Methods
    public static void main(String[] args) {
        // Program execution starts here
        System.out.println("Welcome to Java Programming!");
    }
}
```

### Key Points
- **Case Sensitivity**: Java is case sensitive (`main` ≠ `Main`).
- **File Naming**: The filename must match the public class name (e.g., `BasicSyntax.java`).
- **Curly Braces** define code blocks (`{...}`).
- **Semicolons** end statements.

---

## 4. Data Types

Java has two broad categories:
1. **Primitive Types**: Built-in, storing simple values directly.
2. **Reference Types**: Storing references (pointers) to objects in memory (e.g., `String`, arrays, custom classes).

### 4.1 Primitive Data Types
| Type     | Size (bits) | Range (approx)            | Default Value (fields) |
|----------|-------------|----------------------------|------------------------|
| `byte`   | 8           | -128 to 127               | 0                      |
| `short`  | 16          | -32,768 to 32,767         | 0                      |
| `int`    | 32          | ~ -2.1B to 2.1B           | 0                      |
| `long`   | 64          | ~ ±9.2e18                 | 0L                     |
| `float`  | 32          | ±3.4e38 (7 digits)        | 0.0f                   |
| `double` | 64          | ±1.7e308 (15 digits)      | 0.0d                   |
| `char`   | 16          | '\u0000' to '\uffff'      | '\u0000'               |
| `boolean`| 1 (JVM dep.)| `true` or `false`         | `false`                |

**Example: Printing out data type sizes**
```java
System.out.println("int size: " + Integer.SIZE + " bits");
System.out.println("double size: " + Double.SIZE + " bits");
```

### 4.2 Reference Data Types
- **Strings**, **arrays**, and **classes** (e.g., `String name = "John";`)
- Default value for a reference type field is `null` (meaning no object reference).

---

## 5. Variables

A **variable** is a named memory location that can store a value.

### 5.1 Declaration and Initialization
```java
int age;          // Declaration
age = 25;         // Initialization

double price = 19.99; // Declare and initialize
```

### 5.2 Variable Scope
- **Local Variables**: Declared inside methods; only accessible within that block.
- **Instance Variables**: Declared at the class level (non-static fields).
- **Static Variables**: Shared among all instances of a class.

### 5.3 Example
```java
public class Rectangle {
    private int length;  // instance variable
    private int width;   // instance variable

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getArea() {
        // local variable 'area' is valid only within this method
        int area = length * width;
        return area;
    }
}
```

---

## 6. Operators

Operators perform operations on variables and values. Java categories include:

### 6.1 Arithmetic Operators
- `+` (addition), `-` (subtraction), `*` (multiplication), `/` (integer division), `%` (modulus).
```java
int a = 10, b = 3;
System.out.println(a + b); // 13
System.out.println(a - b); // 7
System.out.println(a * b); // 30
System.out.println(a / b); // 3 (integer division)
System.out.println(a % b); // 1
```

### 6.2 Relational Operators
- `>` (greater), `<` (less), `>=` (greater or equal), `<=` (less or equal), `==` (equal), `!=` (not equal)
```java
System.out.println(a > b);  // true
System.out.println(a == b); // false
```

### 6.3 Logical Operators
- `&&` (AND), `||` (OR), `!` (NOT)
```java
boolean x = true, y = false;
System.out.println(x && y); // false
System.out.println(x || y); // true
System.out.println(!x);     // false
```

### 6.4 Bitwise Operators
- `&`, `|`, `^` (XOR), `~` (NOT), `<<` (left shift), `>>` (right shift)
```java
int c = 5, d = 3; // 5 in binary = 0101, 3 = 0011
System.out.println(c & d);  // 1 (0001)
System.out.println(c | d);  // 7 (0111)
System.out.println(c ^ d);  // 6 (0110)
System.out.println(~c);     // -6 (Two's complement)
System.out.println(c << 1); // 10 (1010)
System.out.println(c >> 1); // 2  (0010)
```

---

## 7. Control Statements

Control statements direct the flow of execution in a program.

### 7.1 `if-else`
```java
int number = -5;
if (number > 0) {
    System.out.println("Positive");
} else if (number < 0) {
    System.out.println("Negative");
} else {
    System.out.println("Zero");
}
```

### 7.2 `switch-case`
```java
int day = 3;
switch(day) {
    case 1: System.out.println("Monday"); break;
    case 2: System.out.println("Tuesday"); break;
    case 3: System.out.println("Wednesday"); break;
    // ...
    default: System.out.println("Invalid day");
}
```

### 7.3 Loops

#### 7.3.1 `for` Loop
```java
for (int i = 1; i <= 5; i++) {
    System.out.println("i = " + i);
}
```

#### 7.3.2 `while` Loop
```java
int x = 1;
while (x <= 5) {
    System.out.println("x = " + x);
    x++;
}
```

#### 7.3.3 `do-while` Loop
```java
int y = 1;
do {
    System.out.println("y = " + y);
    y++;
} while (y <= 5);
```

---

## 8. Functions: Value vs. Reference Types

In Java, **all function arguments are passed by value**—the method receives a copy of the variable. For reference types (like arrays or objects), the *copy of the reference* is passed.

### 8.1 Primitive Parameters (Value Types)
If you pass an `int`, `double`, etc., to a method, any changes within the method **do not** affect the original variable outside the method.

```java
public void changeValue(int x) {
    x = 100; // This change won't affect the original variable
}
```

### 8.2 Reference Parameters
For objects and arrays, the **reference** is copied. However, the **object** it points to can be modified, so changes are visible outside the method.

```java
public void changeArray(int[] arr) {
    arr[0] = 99; // The underlying array is modified
}

int[] numbers = {1, 2, 3};
changeArray(numbers);
// numbers[0] is now 99
```

---

## 9. Concept of References in Java

**References** in Java are like pointers that automatically handle memory in a safer way (no pointer arithmetic). Important implications:

1. **Null Reference**: A reference variable with `null` points to no object. Accessing a null reference causes a `NullPointerException`.
2. **Assignment**: When you assign one object reference to another, both references point to the same object in memory.
3. **Equality**:
   - The `==` operator checks if two references point to the **same** object.
   - The `.equals()` method typically checks if two objects have **equivalent content** (depending on how `.equals()` is overridden).

**Example:**
```java
String a = "Hello";
String b = "Hello";

System.out.println(a == b);       // true (both refer to same interned literal)
System.out.println(a.equals(b));  // true (content is the same)

String c = new String("Hello");
System.out.println(a == c);       // false (different objects in memory)
System.out.println(a.equals(c));  // true (same content)
```

---

## 10. Additional Topics & Resources

### 10.1 Object-Oriented Programming Concepts (Refer to the PPT shared in Java class WhatsApp announcement group)
- **Classes and Objects**  
- **Inheritance**  
- **Polymorphism**  
- **Encapsulation**  

### 10.2 Exceptions (Will be covered in detail in upcoming Units)
Learn about **try-catch-finally** blocks and `throws` for handling runtime errors.

### 10.3 Collections
Introduction to **Lists**, **Sets**, **Maps** in the Java Collections Framework.

### 10.4 Useful Links
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)  
- [Baeldung Java Tutorials](https://www.baeldung.com/)  
- [Gradle Build Tool](https://docs.gradle.org/current/userguide/userguide.html)  
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)  

---

# Summary

These fundamental topics provide the groundwork for further Java exploration. Understanding:
1. How a **basic Java program** is structured, compiled, and run.
2. **Data types** (primitive vs. reference) and **variables** (scope, initialization).
3. Applying **operators** (arithmetic, relational, logical, bitwise) correctly.
4. Utilizing **control statements** (`if-else`, `switch-case`, loops) to direct program flow.
5. Differentiating between **value** and **reference** semantics in method calls.
6. Knowing how **references** work under the hood, including the implications for `==` vs `.equals()`.
