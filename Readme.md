
# Trello API Testing Project

## Table of Contents
- [Description](#description)
- [Tools and Technologies](#tools-and-technologies)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Test Excution](#test-excution)
- [Configuration](#configuration)
- [Authors](#authors)

## Description
TrelloApisRestAssured is a test automation framework tailored for interacting with Trello's API through RESTful requests. It provides a structured approach to testing and validating various API functionalities, ensuring reliability and efficiency in the testing process. This framework facilitates testing functionalities for creating, retrieving, updating, and deleting resources using the RestAssured library.

## Tools and Technologies
<a href="https://www.java.com/en/"><img src="https://cdn-icons-png.flaticon.com/512/226/226777.png" alt="c" width="60" height="60"/> </a>
<a href="https://rest-assured.io/"><img src="https://avatars.githubusercontent.com/u/19369327?s=200&v=4" alt="c" width="60" height="60"/> </a>
<a href="https://testng.org/"> <img src="https://howtodoinjava.com/wp-content/uploads/2014/12/TestNG.png" width="60" height="60"/> </a>
<a href="https://allurereport.org/"> <img src="https://avatars.githubusercontent.com/u/5879127?s=280&v=4" width="60" height="60"/> </a>

## Installation
To set up the project locally, follow these steps:

1. Clone the repository (using bash or Github Desktop):
    ```bash
    git clone https://github.com/MahmoudFawzyAOE2/Trello_API_Testing_Project.git
    ```
2. Open the Project in a proper IDE (Intelij Idea, Eclipse,...)
   
6. Reload the project to ensure that the dependencies are downloaded correctly

## Project Structure
This Projcet include testing requests related to 4 functionalites, each in a separate directory
* Board
* List
* Card
* Attachment

> **When running tests independently, tests need a pre-condition to excute properly.**
> **These pre-conditions are other test methods in same or another class**
>
> **This chart shows the dependenies among other methods**

<div align="center"><img src="https://github.com/user-attachments/assets/97595214-b76c-466f-a36d-07eed2d69c12" alt="c" width="800" height="550"/> </div>

## Test Excution
To run the entire test scenario, excute `TestNG.xml` file. or run a single test independently.

Test results will be available in the `allure-results` directory [allure setup needed]

```bash
allure serve PATH\TO\PROJECT\Trello_API_Testing_Project\allure-results
```

<div align="center"><img src="https://github.com/user-attachments/assets/9b090ff3-5c55-479f-bc34-b389eac30be1" alt="c" width="800" height="300"/> </div>
<div align="center"><img src="https://github.com/user-attachments/assets/80431601-a952-4172-bbe1-2f800c2a806e" alt="c" width="800" height="550"/> </div>

## Configuration
Update the `TestNG.xml` file to configure the test suite. 

API keys and tokens for Trello API access are provided in `AuthCredentials.java`, It's recommended to change them with your own.

## Authors
- Mahmoud Fawzy - [MahmoudFawzyAOE2](https://github.com/MahmoudFawzyAOE2)
