## HR system

An information system for HR department, allows managing employees and getting some statistics about them.
It uses next dependencies:

* Spring Web for building rest web
* Spring Data JPA and HyperSQL Database for storing and manipulating data
* Spring Boot DevTools for testing

## Building The Code

To build the code, follow these steps.

1. Ensure that [Gradle 4+](http://www.gradle.org/) is installed.

2. To build the code, execute the command from the project folder:

  ```shell
  ./gradlew clean build
  ```

3. You will find the compiled code in the `build` folder

## Running the tests

The tests could be run by command

  ```shell
  ./gradlew test
  ```

If they are finished unsuccessfully, the report would be generated.

## Running program

For running program you need [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later
The program could be started with the command 

  ```shell
  java -jar path/to/jar/hr_system-0.0.1-SNAPSHOT.jar
  ```