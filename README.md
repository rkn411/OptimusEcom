 ## OptimusEcom -Web Automation Using POM Approach with Testng Framework

This sample project to demonstrate what is page object model framework and how it can used in selenium to automate any application.
TestNG is used as test framework. Each page is defined as its own class.
Actions (including navigation) are represented as functions for a class.
Tests only talk to the page objects.
Page objects only talk to the driver.
Elements on the page are stored as variables for the page object
Class Inheritance can be used to define functionality to a set of pages

# Prerequisites:
 > Selenium WebDriver
 > WebDriverManager
 > TestNG
 > ExtentReport
 > Apache POI
 > Maven
Note: Please check pom.xml file for all the required dependencies
# Features Available
     Parallel Testing
     CrossBrowser Testing
     ExtenReport Generation
# To run TestNG suite file:
Run tests:

Project link :https://github.com/rkn411/OptimusEcom.git Clone or import project /Download as zip.

Navigate to project root.

mvn clean mvn install

(or)
 Click on src/main/resources/TestSuites/  click on parrallelbroswersuite.xml 

# View HTML Report
HTML report will be generated once execution finish reports.html Open Index.html in browser to see the reports

Path to Test Report file-
/usr.dir/Reports/.html
