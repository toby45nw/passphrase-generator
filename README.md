# passphrase-generator
A secure passphrase generator written in Java, offering users full control over the number of words, special characters, and numbers included in the passphrase.

## Features
*   Generates passphrases based on the Diceware method.
*   Uses the Electronic Frontier Foundation (EFF) large wordlist.
*   Allows customization of passphrase length (number of words).
*   Supports inclusion of special characters, capital letters, and digits.
*   Employs `java.security.SecureRandom` for cryptographically strong randomness.

## Technologies Used
*   **Java 21 LTS**: The core programming language.
*   **Maven**: For build automation and dependency management.
*   **JUnit 5**: Testing framework for unit and integration tests.
*   **Mockito**: Mocking framework for isolating units under test.
*   **Java Records**: Utilized for concise and immutable data classes.

## Getting Started

### Prerequisites
*   Java Development Kit (JDK) 21 or higher
*   Apache Maven

### Building the Project
1.  Clone the repository:
    ```bash
    git clone https://github.com/your-username/passphrase-generator.git
    cd passphrase-generator
    ```
2.  Build the project using Maven:
    ```bash
    mvn clean install
    ```
    This command compiles the source code, runs tests, and packages the application into a JAR file in the `target/` directory.

### Running the Application
After building, you can run the application from the command line:

```bash
java -jar target/passphrase-generator-1.0.0-jar-with-dependencies.jar [-w words] [-s symbols] [-c capitals] [-d digits]
```

**Options:**
*   `-w <number>`: Number of words (default: 3)
*   `-s <number>`: Number of symbols (default: 2)
*   `-c <number>`: Number of capital letters (default: 2)
*   `-d <number>`: Number of digits (default: 2)
*   `-h, --help`: Show help message

**Example:**
```bash
java -jar target/passphrase-generator-1.0.0-jar-with-dependencies.jar -w 5 -s 1 -c 1 -d 1
```

### Running Tests
To execute the project's tests, use Maven:
```bash
mvn test
```

## Credits

This project is a Java implemetation of the Diceware passphrase generation method.

The original idea for this came from [Diceware](https://diceware.dmuth.org/), which is based on the original work by Arnold G. Reinhold. This project uses the Electronic Frontier Foundation (EFF) word list.

*   **Diceware Method:** [https://diceware.dmuth.org/](https://diceware.dmuth.org/)
*   **EFF Word List:** [https://www.eff.org/files/2016/07/18/eff_large_wordlist.txt](https://www.eff.org/files/2016/07/18/eff_large_wordlist.txt)

Diceware is a trademark of A G. Reinhold.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.