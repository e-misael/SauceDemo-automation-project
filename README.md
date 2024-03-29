# SauceDemo-automation-project
Some automation scenarios for SauceDemo.com site using Selenium WebDriver, Java17 and JUnit5.

Project created using PageObjects concepts.

## Running the project
### Precondition
1. Ensure you have JDK17, Maven, and Allure set in your environment.

### Steps
1. Clone the project.
2. Open the terminal.
3. Navigate to the project folder.
4. Run the command "mvn clean test". Alternatively, it's possible to run tests filtered by tag using the command: "mvn run -Dgroups=smoke".

### Checking the test-result
1. Open the "target" folder.
2. Run the "allure serve" command.
3. The report should be displayed in the browser.

Alternatively,
1. Open the target/allure-results folder.
2. Run the command "allure generate".
3. Run the command "allure open".

