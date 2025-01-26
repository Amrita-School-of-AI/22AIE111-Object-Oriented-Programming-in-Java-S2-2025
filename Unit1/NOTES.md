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
