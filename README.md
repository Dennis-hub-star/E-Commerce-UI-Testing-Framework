# Hybrid Automation Testing Framework – e-Commerce & Git Repository API


__1.__   Table of Contents

__2.__   Project Overview

__3.__   Tech Stack & Tools

__4.__   Project Architecture

__5.__   Features

__6.__   Test Data Management

__7.__   Running the Tests

__8.__   Reporting & Utilities

__9.__   Test Suites

__10.__  Project Highlights / Portfolio Value

__11.__  Future Enhancements


___


## Project Overview

This is a **hybrid automation testing framework** designed to validate both **frontend UI** and **backend API** functionalities using industry-standard tools and best practices.

+ **UI Automation**: Tests the ecommerce-playground demo site covering search, add-to-cart, checkout, login, registration, and order history scenarios.

+ **API Automation**: Tests Git Repository API endpoints for create, update, delete, and fetch repository functionalities, including positive and negative test scenarios.

The framework emphasizes **modularity**, **reusability**, and **real-world testing practices**, making it ideal for portfolio demonstration and professional QA roles.


___


## Tech Stack & Tools


+ **Programming Language**: Java

+ **UI Automation**: Selenium WebDriver, TestNG

+ **API Automation**: REST Assured

+ **Build & Dependency Management**: Maven

+ **Reporting**: Extent Reports, TestNG reports

+ **Data Management**: JSON files, TestNG DataProviders

+ **Utilities***: Email notifications, zip utilities, reusable common classes, API utilities, Ui utilities

+ **IDE**: Eclipse 

___

## Project Architecture

## Project Structure

```bash
src
├── main/java
│   └── resources
└── test/java
    ├── api                  : API testing
    │   ├── base             : Base classes for API tests
    │   ├── dataProviders
    │   │   ├── negativeTestsData : Data providers for negative API tests
    │   │   │   ├── createRepo
    │   │   │   ├── deleteRepo
    │   │   │   ├── getRepo
    │   │   │   └── updateRepo
    │   │   └── positiveTestsData : Data providers for positive API tests
    │   ├── endpoints        : User endpoints and URL routes
    │   ├── payload          : API payload classes
    │   ├── pojos            : API POJOs (positive & negative)
    │   ├── test
    │   │   ├── negativeTests : Negative API tests
    │   │   └── positiveTests : End-to-end positive API tests
    │   └── utilities        : API-specific helper utilities
    ├── common               : Shared utilities for UI & API
    └── ui                   : UI testing
        ├── base             : Base test class with @BeforeMethod and @AfterMethod
        ├── components       : Page components (sidebar, navbar, etc.)
        ├── dataProviders    : UI test data providers (positive & negative)
        ├── ExtentReportsNG  : Generates Extent Reports object
        ├── Listeners        : TestNG listeners
        ├── pages            : Page object classes (login, register, search, checkout, add-to-cart, confirm order, order history)
        ├── tests
        │   ├── negativeTests : Negative UI test cases
        │   └── positiveTests : Positive UI test cases
        └── utils
            └── annotation   : Email utilities, zip utilities, and other reusable UI utilities

```

**Resources Structure:**

```bash
src/test/resources
├── api
│   └── testData
        ├── positiveTestData : JSON files for positive API tests
│       └── negativeTestData : JSON files for negative API tests
└── ui
    ├── data                 : JSON test data for UI tests
    │   ├── negative_test_data
    │   └── positive_test_data
    └── properties           : Configurations and properties files
```

**Reports & Test Output:**

+ TestNG output: `test-output/`

+ Extent Reports: `reports/`

**Test Suites:**

+ `hybridE2ETest.xml`

+ `loginSuite.xml`

+ `registrationSuite.xml`

+ `negativeAPITestsSuite.xml`

+ `negativeUITestsSuite.xml`

---

 ## Features

**UI Automation Features**

+ End-to-end scenarios: login, registration, search, add-to-cart, checkout, confirm order, order history

+ Positive and negative test coverage

+ Page Object Model (POM) design for modularity

+ Component-based reusable classes for UI elements

+ Data-driven tests via JSON + TestNG DataProviders

**API Automation Features**

+ CRUD operations on Git Repository API

+ Positive & negative test scenarios

+ POJO-based payload and response validation

+ Dynamic test data for robustness

+ Shared utilities to reduce code duplication


**Cross-Cutting Features**

+ Extent Reports for detailed, visual test reporting

+ TestNG listeners for capturing screenshots on failure

+ Email notifications for test results

+ Modular, reusable utility classes

----

## Test Data Management

+ **UI Test Data**: JSON files under `src/test/resources/ui/data/`

+ **API Test Data**: JSON files under `src/test/resources/api/testData/`

+ **DataProviders**: Java classes under `dataProviders` packages provide dynamic input for both positive and negative tests

Ensures **data-driven**, **maintainable**, and **scalable** test coverage.

---

## Running the Tests

**Prerequisites:**

+ Java JDK 8+

+ Maven installed

+ IDE: Eclipse or IntelliJ IDEA

+ Internet connection for ecommerce-playground and GitHub API

**Steps:**

**1.** Clone the repository

```bash
git clone <your-repo-url>
cd my_e-Commerce_Site_Project
```

**2.** Run tests via TestNG XML suite

```bash
mvn test -DsuiteXmlFile=testSuites/hybridE2ETest.xml
```


**3.** Install dependencies via Maven

```bash
mvn clean install
```

**4.** View reports in:

+ Extent Reports: `reports/`

+ TestNG Reports: `test-output/`

---

## Reporting & Utilities

+ **Extent Reports**: Provides detailed HTML reports with screenshots and logs

+ **Listeners**: Capture screenshots on test failure, track suite progress

+ **Email Utility**: Sends email reports post-execution

+ **Zip Utility**: Archives reports for distribution or storage


## Test Suites

+ **hybridE2ETest.xml** – Runs full end-to-end tests across UI and API

+ **loginSuite.xml** – UI login tests

+ **registrationSuite.xml** – UI registration tests

+ **negativeAPITestsSuite.xml** – Negative API tests for CRUD operations

+ **negativeUITestsSuite.xml** – Negative UI tests

Each suite is **modular**, allowing selective execution for faster feedback or full regression runs.


---

## Project Highlights / Portfolio Value

+ Real-world hybrid framework combining UI + API testing

+ Implements **modular**, **reusable**, and **scalable** architecture

+ **Data-driven testing** with positive & negative coverage

+ Professional reporting via **Extent Reports & TestNG**

+ Demonstrates **industry best practices:** POM, TestNG, REST Assured, utilities, listeners, and suite management

+ Ready to showcase on **GitHub portfolio** for recruiters



