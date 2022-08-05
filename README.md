# Cucumber And RestAssured
How tu run
add Gradle run
:test --tests "runner.TestRunner"

Behavior Driven Development (BDD) has become a popular approach in communicating requirements between stakeholders of agile teams. In fact, it’s so effective that it’s also being adopted in automation strategies by using Cucumber to write test scenarios in Gherkin (a non-technical, human readable language) and coupling them with an automation framework so that the scenarios are executable in the form that they are originally written.

While many teams use Cucumber to describe their UI testing, this open source software can be used for web service scenarios as well.

For example, here’s a simple scenario that tests Google’s Books API to get a book by its ISBN. This is written in a feature file using Cucumber.

REST API Test in Cucumber BDD Style Test
We will use the Cucumber BDD Framework to execute our tests. Additionally, it would require us to Convert our Rest Assured API Tests to the Cucumber BDD Style Test.

The following steps will help us to achieve this:

Add Cucumber Dependencies to the Project
To build a test framework, we need to add several dependencies to the project. This can be achieved by any build tool. I have used Gradle Build Tool. Click here to know How to install Gradle.
'''
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation "io.cucumber:cucumber-java:7.2.3"
    testImplementation "io.cucumber:cucumber-junit:7.2.3"
    testImplementation "org.junit.vintage:junit-vintage-engine:5.7.0"
    testImplementation 'org.json:json:20160810'
    testImplementation 'io.rest-assured:rest-assured:4.5.0'
}
'''

Tools/Framework/Libraries
JDK 17
Maven/Gradle - build tool
TestNG - test runner
Cucumber - BDD/Gherkin style feature files
Rest assured - Rest api verification library


#BDD (Feature file / Step definition)
BDD requires a feature file to invoke the step definitions:

Create the scenarios in feature file as per the requirements, so each step in feature file has to match a step definition in class file;
Following the BDD practices for coding;
Using the special annotation like "@Before" which is the first method to run for each scenario. Moreover, this is the right place to set up the URI (endpoint) which will be called by HTTP request;