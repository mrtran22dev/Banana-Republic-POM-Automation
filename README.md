# Banana-Republic-POM-Automation
Java Selenium TestNG Web Automation

Banana Republic Web Checkout Automation implementing Page Object Modeling design pattern + Java/Selenium/TestNG/Apache POI

SCOPE: This purpose of this project is to implement Page Object Modeling (POM) design pattern in conjunction with Java/Selenium/TestNG/Apache POI to automate and validate the critical functions of the Banana Republic website.  The critical functions automated and validated with the page objects/test cases are:
 - logging in user account
 - searching for items
 - adding item to cart/shopping bag
 - verify correct item added to cart/checkout
 - checking out the item in cart

CORE CONCEPTS: 
- Page Object Modeling (POM) Design Pattern implemented with Java language
- Apache POI library to create Read/Write Utility to get/set actual and expected data from Excel file
- TestNG framework to create test cases and XML file to implement test suite execution and reporting

NOTE: The following folder path/location descriptions are for reference:
1. src > main - source code files for test base class, page classes, and utilies
2. src > test - corresponding test classes and class, testng.xml (test suite file)
3. expectedData.xlsx - Excel file to read/write actual vs. expected data
