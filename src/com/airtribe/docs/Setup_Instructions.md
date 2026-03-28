# Java Setup Instructions

## JDK Version Used

| Property | Value |
|----------|-------|
| **JDK Version**  | Java 17.0.15 |
| **Java Edition** | Java SE 17 (LTS) |
| **Release Type** | Long-Term Support (LTS) |

To verify your installation, run the following in your terminal:

```bash
java -version
javac -version
```

Expected output:

```
java version "17.0.15" 2025-04-15 LTS
Java(TM) SE Runtime Environment (build 17.0.15+...)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.15+..., mixed mode, sharing)

javac 17.0.15
```

---

## Hello World — Step-by-Step

### Step 1: Write the Program

Create a file named `HelloWorld.java` with the following content:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

> **Important:** The filename must exactly match the class name — `HelloWorld.java`.

---

### Step 2: Compile the Program

Open your terminal in the same folder as `HelloWorld.java` and run:

```bash
javac HelloWorld.java
```

This uses the **Java Compiler (`javac`)** to convert your source code into **bytecode**.  
After running this, you will see a new file created: `HelloWorld.class`

```
your-folder/
├── HelloWorld.java      ← your source code
└── HelloWorld.class     ← compiled bytecode (created by javac)
```

---

### Step 3: Run the Program

```bash
java HelloWorld
```

> Note: Do **not** include `.class` when running — just use the class name.

---

### Step 4: Expected Output

```
Hello, World!
```

That's it! Your first Java program is running successfully.

---

## What Each Part of the Code Means

```java
public class HelloWorld {          // Defines a class named HelloWorld
    public static void main(String[] args) {   // Entry point — JVM starts here
        System.out.println("Hello, World!");   // Prints text to the console
    }
}
```

| Keyword | Meaning |
|--------|---------|
| `public` | Accessible from anywhere |
| `class` | Blueprint/structure in Java |
| `static` | Belongs to the class, not an object |
| `void` | This method returns nothing |
| `main` | The starting point of every Java program |
| `System.out.println` | Prints a line to the terminal |

---

## Troubleshooting

| Problem | Likely Cause | Fix |
|--------|--------------|-----|
| `javac: command not found` | JDK not installed or PATH not set | Install JDK 17, set `JAVA_HOME` |
| `error: class HelloWorld is public, should be in a file named HelloWorld.java` | Filename mismatch | Rename the file to match the class name exactly |
| `could not find or load main class HelloWorld` | Running from wrong directory | `cd` into the folder containing `HelloWorld.class` |
