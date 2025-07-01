# entrataAssessment


# Selenium Java Automation Framework

This project is a simple test automation framework using **Selenium WebDriver**, **Java**, and **TestNG** to automate and validate UI interactions such as form filling, button navigation, and tooltip validations.

---

## Project Structure

- src:
    - hooks # Base test setup (WebDriver init, teardown)
    - pages # Page Object Model classes (homePage, watchDemoPage)
    - tests # Test classes (demoFormTest)
- pom.xml # Maven dependencies
- README.md # Project documentation



---

## Prerequisites

- Java 17+ installed
- Maven installed and added to PATH
- Chrome browser installed
- Internet access for WebDriverManager to download drivers

---

## Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager (for auto driver handling)

---

# Setup Instructions

1: Clone repository

    bash command: 
    git clone https://github.com/your-username/your-repo-name.git
    cd your-repo-name

2: Install Dependencies

    bash command: mvn clean install

3: Run Tests

    bash command: mvn test