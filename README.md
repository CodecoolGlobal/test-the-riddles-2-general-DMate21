# Reptile Riddles 2 - Automated Testing Project

## Overview

This project is an automated testing setup for **Reptile Riddles 2** - a dynamic web application built for quizzing users. The purpose of this repository is to verify the functionality of the **Quiz** application using Selenium WebDriver and JUnit 5. The tests cover various user interactions, such as user registration, quiz creation, answering questions, and viewing results.

**Reptile Riddles 2** is designed to provide an interactive and fun quiz experience. Users can create quizzes, challenge others, and see results. This application is built with **React** for the frontend and **Java Spring Boot** for the backend, using a **PostgreSQL** database for storage.

## Technologies Used

- **Java 22**: The programming language used for writing test scripts.
- **Selenium WebDriver**: For automating browser interactions.
- **JUnit 5**: For running and structuring tests.
- **PostgreSQL**: Database used to store user and quiz data.
- **Dotenv**: Used for managing environment variables.

## Features

The tests in this repository cover multiple functionalities of the QuizBlast application:

- **User Registration**: Verify that users can register by selecting a username and password.
- **Login**: Ensure users can log in and optionally stay logged in.
- **Quiz Master Actions**:
  - Create, modify, and delete quizzes.
  - Add multiple answer options to each question.
  - Set time limits for questions to adjust difficulty.
  - Share quizzes and open a lobby for a quiz.
- **Player Actions**: Verify that players can join quizzes, answer questions, and see results after the game ends.
  
The project is configured to run tests against a **dockerized instance** of the application (the System Under Test).

## Installation

To set up and run the project locally, follow these steps:

### 1. Clone the repository:

```bash
git https://github.com/CodecoolGlobal/test-the-riddles-2-general-DMate21
```
```bash
cd test-the-riddles-2-general-DMate21
```

### 2. Install Maven dependencies:

Ensure that you have Java 22 installed. Then, use Maven to install the required dependencies for running the tests:

```bash
mvn install
```

### 3. Prepare Environment Variables:
This project uses environment variables to store sensitive data, such as database credentials and other configuration. Make sure to set up a .env file in the root directory of the project. You can define your database credentials and any other necessary environment variables in this file.

Example .env file:

```
PLAYER=john_doe
PLAYER_EMAIL=john.doe@example.com
PLAYER_PASSWORD=securepassword123

BASE_URL=http://localhost:3000

GAMEMASTER=quiz_master
GAMEMASTER_PASSWORD=masterpassword456
GAMEMASTER_EMAIL=quizmaster@example.com
```

### 4. Run the tests:
To execute the tests, run the following Maven command:

```bash
mvn test
```
This command will run the tests and output the results in the terminal.

## Test Scenarios

The automated tests in this project cover a wide range of functionalities, including but not limited to:

User Registration and Login:

Ensure new users can choose a username and password during registration.
Verify that users can stay logged in across sessions.
Quiz Master Features:

Test quiz creation, modification, and deletion.
Verify that quiz masters can add multiple answer options and set time limits.
Ensure the ability to share quizzes and invite other players.
Player Features:

Verify that players can join quizzes, answer questions, and view results after the game ends.