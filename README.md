# Object Oriented Programming in Java - General Instructions on how to use the repository

Welcome to the **Object Oriented Programming in Java** repository! This repository contains notes and code examples organized into units, helping you understand and practice Java concepts step-by-step.

---

## Repository Structure
The repository is organized into four main folders, each representing a unit of the course:

- **Unit1/**
  - `NOTES.md` – Markdown notes covering fundamental OOP in Java concepts.
  - `code/` – Example Java programs (subfolders).
- **Unit2/**
  - `NOTES.md`
  - `code/`
- **Unit3/**
  - `NOTES.md`
  - `code/`
- **Unit4/**
  - `NOTES.md`
  - `code/`

Additionally, you will find:
- **.vsode/**, **.idea/** – Contains setup files for Visual Studio Code (VS Code) and IntelliJ IDEA to help you load and run the Java projects in each of the subfolders.

---

## How to Use This Repository

1. **Browse the Notes**  
   Within each unit folder, find the `NOTES.md` Markdown (`.md`) files. These notes will guide you through the theoretical concepts of each unit.

2. **Explore the Code Examples**  
   In the `code` subfolder under each unit, you will find practical Java examples illustrating the concepts discussed in the notes. Each example might be in its own subfolder.

3. **Run and Experiment**  
   Feel free to modify the example code or create your own Java files to practice. Instructions on setting up Java and running the code from the command line are provided below.

4. **Setup Files**  
   - If you prefer using **VS Code**, refer to the files in the `setup/vscode` directory.  
   - If you prefer using **IntelliJ IDEA**, refer to the files in the `setup/idea` directory.

---

## Setting up Java on Linux (Ubuntu)

You have two main options for installing the Java Development Kit (JDK): **Oracle JDK** or **OpenJDK**. Both will allow you to compile and run Java programs.

### 1. Installing OpenJDK (Recommended)
OpenJDK is open-source and is typically the easiest to install on Ubuntu:
```bash
sudo apt update
sudo apt install openjdk-21-jdk
```
*(You can replace `21` with the version you need, e.g., `openjdk-11-jdk`.)*

### 2. Installing Oracle JDK
If you prefer Oracle’s official JDK:
1. Download the `.deb` package from the [Oracle Official Website](https://www.oracle.com/java/technologies/downloads/).
2. Navigate to the directory where the package is saved:
    ```bash
    cd /path/to/download
    ```
3. Install the package:
    ```bash
    sudo dpkg -i <oracle-jdk-package>.deb
    ```
4. Verify installation:
    ```bash
    java -version
    ```
    You should see the Oracle JDK version displayed.

---

## Running Java Programs from the Command Line

After installing Java (whether OpenJDK or Oracle JDK), you can compile and run the example code from the command line. Below is a general step-by-step guide:

1. **Open a Terminal**: Navigate (`cd`) to the directory containing the Java files you want to compile. For instance:
   ```bash
   cd /path/to/Unit1/code/Example1
   ```

2. **Compile the Java File(s)**:  
   ```bash
   javac Main.java
   ```
   - `javac` is the Java compiler.
   - This will generate one or more `.class` files in the same directory.

3. **Run the Java Program**:  
   ```bash
   java Main
   ```
   - Make sure **Main** is the class that contains the `public static void main(String[] args)` method.

4. **Passing Arguments (Optional)**:  
   If your program requires command-line arguments, you can pass them like so:  
   ```bash
   java Main arg1 arg2
   ```

---

## Loading the Projects in VS Code or IntelliJ IDEA

### Visual Studio Code
1. Install the **Java Extension Pack** from the VS Code Marketplace.
2. Copy or clone this repository to your local machine.
3. Open the root of this repository with VS Code.
4. The extension pack will detect the Java projects, and you can start editing, compiling, and debugging Java code directly within VS Code.

### IntelliJ IDEA
1. Install IntelliJ IDEA (Community Edition is sufficient).
2. Clone this repository to your local machine.
3. From the IntelliJ IDEA **Welcome** screen, select **Open** or **Import**.
4. Navigate to this repository’s root folder (or a specific unit’s `code` subfolder) and import it as a Maven/Gradle project if applicable, or simply as a Java project.
5. IntelliJ will automatically configure the project’s libraries and dependencies. 

---

## Contributing
If you find any issues or want to suggest improvements:
- **Open an Issue**: Describe the problem or the idea you have.
- **Submit a Pull Request**: Fork this repository, make changes, and submit a pull request for review.

---

## License
This repository is intended for educational purposes. Feel free to fork and modify for your personal study or classroom use. If you plan to use it beyond that, please check the specific licensing details or contact the authors of the original work.

---

**Happy Coding!**  
For any questions or clarifications regarding the course materials, feel free to reach out to me at `a_abhijith@cb.amrita.edu` or open an issue in this repository. Enjoy learning **Object Oriented Programming in Java**!
