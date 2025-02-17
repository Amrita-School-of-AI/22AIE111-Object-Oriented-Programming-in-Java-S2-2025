# Java Course - Unit 1: Assignments

This repository contains multiple Java assignments designed to help you learn fundamental concepts, project structure, and automated testing using JUnit and Gradle. Each assignment builds upon key Java concepts.

## Table of Contents
1. [Assignment 1: Hello World Program](#assignment-1-hello-world-program)
2. [Assignment 2: Basic Program Syntax](#assignment-2-basic-program-syntax)
3. [Assignment 3: Data Types](#assignment-3-data-types)
4. [Assignment 4: Variables](#assignment-4-variables)
5. [Assignment 5: Operators](#assignment-5-operators)
6. [Assignment 6: Control Statements](#assignment-6-control-statements)
7. [Assignment 7: Functions—Value Types and Reference Types](#assignment-7-functionsvalue-types-and-reference-types)
8. [Assignment 8: The Concept of References](#assignment-8-the-concept-of-references)
9. [Assignment 9: Creating Java Projects in Different Ways (#assignment-9-creating-java-projects-in-different-ways)

## General Submission Guidelines

1. **Fork the Repository**  
   Click the **"Fork"** button on the top right of this repository page to create your own copy.

2. **Clone Your Fork**  
   ```bash
   git clone https://github.com/<your-username>/java-course-homework.git
   cd java-course-homework
   ```

3. **Implement Each Assignment**  
   - Each assignment can be placed in its own directory (e.g., `Assignment1`, `Assignment2`, etc.).
   - Maintain standard Gradle project structure for each assignment:
     ```
     AssignmentX
     ├── src
     │   ├── main
     │   │   └── java
     │   └── test
     │       └── java
     └── build.gradle
     ```
   - Or maintain a single Gradle project with multiple packages (e.g., `org.example.helloworld`, `org.example.basicsyntax`, etc.).

4. **Run Tests and Verify**  
   - Use Gradle to build and test:
     ```bash
     gradle build
     gradle test
     ```
   - Make sure all tests pass before you commit.

5. **Commit and Push**  
   ```bash
   git add .
   git commit -m "Complete Assignment <X>"
   git push origin main
   ```

6. **Create a Pull Request**  
   - Go to your forked repository on GitHub.
   - Click **Pull Requests** and open a new pull request to merge your changes.

---

## Assignment 1: Hello World Program

### Objective
Implement a simple Java program that returns the string `"Hello World!"`. This assignment will help you understand the basics of Java programming, project structure, and automated testing using JUnit.

### Requirements
1. **Create the `HelloWorld` class**  
   - The class should be named `HelloWorld`.
   - It should contain a method `sayHello()` that returns the string `"Hello World!"`.

2. **Project Structure**  
   - Use Gradle as the build tool.
   - Maintain the standard Gradle project structure.

3. **Automated Testing**  
   - Implement a JUnit 5 test case in `HelloWorldTest` to verify that the `sayHello` method returns the correct string.

---

## Assignment 2: Basic Program Syntax

### Objective
Practice printing a message to the console, reading user input, and doing a simple calculation.

### Requirements
1. **Print** `"Welcome to Java Programming"`.
2. **Prompt** the user for two integers.
3. **Calculate** and **print** their sum.

### Suggested Classes
- `App` (or `BasicSyntaxAssignment`), containing:
  - `sayWelcome()` method returning `"Welcome to Java Programming"`.
  - `sum(int a, int b)` method returning `a + b`.
  - A `main` method that prints the welcome message, reads two integers, and prints their sum.

### Tests
Create a JUnit test class (`AppTest`) that checks:
- `sayWelcome()` returns `"Welcome to Java Programming"`.
- `sum(10, 20)` returns 30.

---

## Assignment 3: Data Types

### Objective
Display the size and default values (for instance fields) of Java’s primitive data types.

### Requirements
1. **List** each primitive type (`byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`).
2. **Show** the size in bits and mention the default values.

### Suggested Classes
- `App`, containing:
  - `getDataTypesInfo()` method returning a `String` describing each primitive type.

### Tests
- Check the returned string to ensure it contains references to each data type.

---

## Assignment 4: Variables

### Objective
Use variables to store dimensions of a rectangle and compute area and perimeter.

### Requirements
1. **Declare** instance variables for `length` and `width`.
2. **Compute** the area (`length * width`) and perimeter (`2 * (length + width)`).

### Suggested Classes
- `App`, containing:
  - A constructor accepting `length` and `width`.
  - `getArea()` and `getPerimeter()` methods.

### Tests
- Use a test that creates a rectangle (e.g., `new App(5, 3)`) and verifies:
  - Area is `15`.
  - Perimeter is `16`.

---

## Assignment 5: Variables

### Objective
Demonstrate the usage of multiple operator categories in Java:
- **Arithmetic operators** (`+`, `-`, `*`, `/`, `%`)
- **Relational operators** (`>`, `<`, `==`, `!=`, `>=`, `<=`)
- **Logical operators** (`&&`, `||`, `!`)
- **Bitwise operators** (`&`, `|`, `^`, `~`, `<<`, `>>`)

### Requirements
1. **Create individual methods** for each operator. For instance:
   - `add(int a, int b)`, `subtract(int a, int b)`, etc.
   - `greaterThan(int a, int b)`, `equalTo(int a, int b)`, etc.
   - `logicalAnd(boolean x, boolean y)`, etc.
   - `bitwiseAnd(int a, int b)`, etc.
2. **Return** the correct result from each method.
3. **Write tests** that validate each operator’s actual output.

### Suggested Classes
- `App`  
  Contains one function per operator. In the `main` method, demonstrate usage by calling these methods with sample inputs.
- `AppTest`  
  Contains assertions that check your method outputs (e.g., `add(10, 3)` should be `13`).

---

## Assignment 6: Control Statements

### Objective
Show proficiency in **if-else**, **switch-case**, **for**, **while**, and **do-while** statements.

### Requirements
Create several methods, each demonstrating a different control statement:
1. **`checkSign(int number)`**  
   - Uses **if-else** to determine if a number is positive, negative, or zero.
2. **`getDayName(int day)`**  
   - Uses a **switch-case** statement to return a weekday name (e.g., `1 -> "Monday"`, `2 -> "Tuesday"`, ...).
   - Return `"Invalid day"` if the integer is outside the range 1-7.
3. **`calculateFactorial(int n)`**  
   - Uses a **for loop** to calculate and return the factorial of `n`.
   - Assume `n >= 0`. (Factorial of 0 is 1.)
4. **`isPrime(int number)`**  
   - Uses a **while** loop (or for loop) to check if `number` is prime.
   - Return `true` if prime, otherwise `false`.
5. **`sumUntil(int limit)`**  
   - Uses a **do-while** loop to sum consecutive integers starting from 1 up to `limit`, returning the total.
   - Assume `limit >= 1`.

In the `main` method, demonstrate each method with sample inputs.

### Suggested Classes
- `App`  
  Contains methods for each control statement. Demonstrate these in `main` with sample inputs.
- `AppTest`  
  Tests each method’s logic, ensuring correct behavior for various inputs.

---

## Assignment 7: Functions—Value Types and Reference Types

### Objective
Understand how Java handles value types (primitives) and reference types (arrays, objects).

### Requirements
1. **swapByValue**: a function that takes two integers and returns them swapped in a new array (demonstrating pass-by-value).
2. **swapByReference**: a function that takes an array of two elements and swaps them in place (demonstrating reference manipulation).

### Suggested Classes
- `App`, containing:
  - `swapByValue(int a, int b)` → returns an `int[]` with `[b, a]`.
  - `swapByReference(int[] arr)` → swaps `arr[0]` and `arr[1]` in place.

### Tests
- Confirm that `swapByValue(5, 10)` returns `[10, 5]`.
- Confirm that an array passed to `swapByReference` is actually modified.

---

## Assignment 8: The Concept of References

### Objective
Illustrate the difference between modifying a primitive vs. modifying an object (reference).

### Requirements
1. **Create** a `Person` class with a `name` field.
2. **changePrimitive(int num)** tries (and fails) to update an `int` parameter.
3. **changeReference(Person person)** successfully updates `person.name`.

### Suggested Classes
- `App`, containing:
  - `changePrimitive(int num)`
  - `changeReference(Person person)`
  - Inner `Person` class with `String name`.

### Tests
- Verify that the integer remains unchanged outside `changePrimitive(...)`.
- Verify that `Person.name` is changed after passing the object to `changeReference(...)`.

---

## Assignment 9: Creating Java Projects in Different Ways 

Please refer to the README.md in the `Assignment9/` folder

---

## Building & Testing with Gradle

From within each assignment’s directory:
```bash
gradle build
gradle test
```
- **`gradle build`** compiles the project.
- **`gradle test`** runs all JUnit tests.

---

## Additional Resources

- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)  
- [Gradle Documentation](https://docs.gradle.org/)  
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

### Happy Coding!

If you have any questions or run into any issues, please open an Issue or start a Discussion in this repository.
