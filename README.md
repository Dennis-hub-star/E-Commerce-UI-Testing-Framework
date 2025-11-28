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

+ TestNG output: test-output/

+ Extent Reports: reports/

**Test Suites:**

+ hybridE2ETest.xml

+ loginSuite.xml

+ registrationSuite.xml

+ negativeAPITestsSuite.xml

+ negativeUITestsSuite.xml

