```markdown
# Assignment: Creating Java Projects in Different Ways

## Overview

In this assignment, you'll learn how to create and run a **simple Java program** in several distinct ways. The program itself is straightforward: it prints `"Learning Java is fun!"` to the console. However, the **focus** is on becoming familiar with **multiple approaches** to setting up and running a Java project, from manual compilation on the command line to using modern build tools and IDEs.

---

## Task Description

Create a Java program called `LearningJava.java` (or `App.java` if you prefer) with a `main` method that prints the message:

```plaintext
Learning Java is fun!
```

The **core** of the assignment is to **practice project creation** under different scenarios:

1. **Create the Java project from the command line** (no build tools, manual compile & run).
2. **Create the Java project using Gradle** (basic Gradle setup and build).
3. **Create the Java project in VSCode** (manually or via project templates).
4. **Create the Java project in VSCode using the Gradle plugin** (integrated build and run).
5. **Create the Java project using IntelliJ IDEA** (standard Java project).
6. **Create the Java project as a Gradle project in IntelliJ IDEA** (integrated Gradle support).

Although the **source code** is the same in all scenarios, you’ll gain experience with different project structures, tools, and IDEs.

---

## Steps to Solve

### 1. Command Line (No Build Tool)

1. **Create a Folder**  
   ```bash
   mkdir learning-java-cli
   cd learning-java-cli
   ```
2. **Write the Code**  
   Create a file named `LearningJava.java`:
   ```java
   public class LearningJava {
       public static void main(String[] args) {
           System.out.println("Learning Java is fun!");
       }
   }
   ```
3. **Compile**  
   ```bash
   javac LearningJava.java
   ```
   This generates `LearningJava.class`.
4. **Run**  
   ```bash
   java LearningJava
   ```
   You should see:
   ```plaintext
   Learning Java is fun!
   ```

### 2. Gradle (Command Line)

1. **Initialize a Gradle Project**  
   From a new directory, run:
   ```bash
   gradle init --type java-application
   ```
   or manually create the necessary `build.gradle`, `settings.gradle`, and `App.java`.
2. **Update the Main Class**  
   By default, Gradle might generate a class `App.java` in `src/main/java`:
   ```java
   public class App {
       public static void main(String[] args) {
           System.out.println("Learning Java is fun!");
       }
   }
   ```
3. **Build and Run**  
   - `gradle build`  
   - `gradle run`  
   If you used the Gradle init template, you can also see a generated test class, which you can modify or remove as needed.

### 3. VSCode (Manually)

1. **Install Extensions**  
   - [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)  
2. **Create a Folder**  
   In VSCode, choose **File → Open Folder** and select or create a new folder, e.g. `learning-java-vscode`.
3. **Create the Java File**  
   Create `LearningJava.java`:
   ```java
   public class LearningJava {
       public static void main(String[] args) {
           System.out.println("Learning Java is fun!");
       }
   }
   ```
4. **Run**  
   - Hover over the main method or the class, and click the **Run** or **Debug** link.  
   - Alternatively, open the Command Palette (`Ctrl + Shift + P`), select **Java: Run**.

### 4. VSCode with Gradle Plugin

1. **Install Gradle for Java Extension**  
   If you prefer a Gradle-based workflow within VSCode, install the [Gradle extension](https://marketplace.visualstudio.com/items?itemName=richardwillis.vscode-gradle).
2. **Initialize a Gradle Project**  
   Similar to step #2 above, but do it within the VSCode terminal or with the Gradle extension’s project creation feature.
3. **Edit `build.gradle`**  
   Ensure the `mainClassName` or `application` plugin points to your main class:
   ```groovy
   plugins {
       id 'java'
       id 'application'
   }
   application {
       mainClass = 'org.example.LearningJava'
   }
   ```
4. **Run**  
   Use the **Gradle Tasks** panel in VSCode to run `build` or `run`.

### 5. IntelliJ IDEA (Standard Java Project)

1. **Create a New Project**  
   - In IntelliJ, select **New Project** → **Java** → pick your desired JDK.
2. **Project Structure**  
   IntelliJ will create a standard folder structure. By default:
   - `src/` → place your `LearningJava.java`.
3. **Write the Code**  
   ```java
   public class LearningJava {
       public static void main(String[] args) {
           System.out.println("Learning Java is fun!");
       }
   }
   ```
4. **Run**  
   - Right-click on the file → **Run 'LearningJava.main()'**.  
   - IntelliJ compiles and executes the code, printing the message to the console.

### 6. IntelliJ IDEA (Gradle Project)

1. **New Project → Gradle**  
   - In the **New Project** wizard, choose **Gradle** as the build system.  
   - Select your JDK.
2. **Project Structure**  
   - IntelliJ auto-generates `build.gradle`, `settings.gradle`, and the appropriate folders.
3. **Add the Main Class** in `src/main/java`:
   ```java
   package org.example;

   public class LearningJava {
       public static void main(String[] args) {
           System.out.println("Learning Java is fun!");
       }
   }
   ```
4. **Sync & Run**  
   - IntelliJ automatically syncs the Gradle project.
   - Use the **Gradle** tool window or the **Run** configuration to build and run.

---

## What You’ll Learn

1. **Command Line Compilation**  
   You’ll understand the raw process (`javac`, `java`) without any automation.

2. **Gradle Builds**  
   You’ll see how a build tool simplifies project organization and dependency management.

3. **VSCode Workflow**  
   Learn about the Java Extension Pack, integrated debugging, and the Gradle plugin.

4. **IntelliJ IDEA Workflow**  
   Gain familiarity with a popular Java IDE, including project wizards and Gradle integration.

---

## Submission Guidelines

1. **Pick at least two approaches** to demonstrate (though experimenting with all is encouraged).  
2. **Push your code** to the repository or share screenshots showing each project structure and run output.  
3. **Include a short note** on which approach you found easiest or most intuitive and why.

---

## Tips & Hints

- **Check Java Version**  
  Ensure that `java -version` displays the correct JDK version you intend to use.
- **IDE vs. CLI**  
  IDEs (like IntelliJ or VSCode) handle many steps automatically (compilation, packaging, etc.), but it’s valuable to understand how to do it manually.
- **Gradle**  
  Let the generated files scaffold your project structure. Spend time reading the `build.gradle` file to understand each line.

---
