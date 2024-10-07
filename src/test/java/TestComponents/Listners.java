package TestComponents;

import CommonFiles.AbstractComponents;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Listners extends BaseClass implements ITestListener {
    ExtentTest test;
    ExtentReports extentReports;
    private ITestContext context;

    //-----------------------Extent ReporterNG method call---------------------------------------

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //  To Solve Concurrency Issue
    String case_Type;

    @Override
    public void onStart(ITestContext context) {

        if (extentReports == null) {
            extentReports = AbstractComponents.getReportObject(context);
        }

    }


    @Override
    public void onTestStart(ITestResult result) {


        LocalTime currentTime = LocalTime.now();

        //-------- Format the time to HH:MM:SS-----------
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String startTime = currentTime.format(formatter);


        //-----------------Create test entry only if not already created (for the first invocation)--------------
//        if (extentTest.get() == null) {

        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
//        }


        //-----------Log the Invocation Count and Start Time--------------------------------
//        extentTest.get().log(Status.INFO, "Invocation Count: " + (result.getMethod().getCurrentInvocationCount() + 1));
        extentTest.get().log(Status.INFO, "Test Start Time: " + startTime);


    }


    @Override
    public void onTestSuccess(ITestResult result) {
        LocalTime currentTime = LocalTime.now();

        //-------- Format the time to HH:MM:SS-----------
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String endTime = currentTime.format(formatter);

        //-----------Log the Test Case Status and End Time--------------------------------

        extentTest.get().log(Status.PASS, "Test Pass");
        ITestContext context = result.getTestContext();
        extentTest.get().log(Status.INFO, "Test End Time: " + endTime);


        // ----------------To Log The Package Name amd Test Case Type of the Test Case----------
        case_Type = (String) context.getAttribute("case_Type");
        String packageName = result.getMethod().getRealClass().getPackage().getName();
        extentTest.get().log(Status.INFO, case_Type + "=>" + packageName);

        //----------------For Getting Test Scenario--------------------------------
        String testScenarioName = (String) context.getAttribute("testScenarioName");
        extentTest.get().log(Status.INFO, "Test Scenario Name: " + testScenarioName);

        //----------------For Changing the Test Name--------------------------------
        String testName = (String) context.getAttribute("testName");
        test.getModel().setName(testName);

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LocalTime currentTime = LocalTime.now();
        int iteration_Count = result.getMethod().getCurrentInvocationCount();
        String screeenShotBase;

        //-------- Format the time to HH:MM:SS-----------
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String endTime = currentTime.format(formatter);


        //------------------Logging Test Failure in the Report----------------
        result.setStatus(ITestResult.FAILURE);
        extentTest.get().log(Status.FAIL, "Test Fail");

        //---------Logging Screenshot Where Test Failed and Test End Time--------------------------------

        try {
            //In below method we are getting test class of testNG->real class of test-> then getting driver field from the class
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")  //Method to get the driver life from the test
                    .get(result.getInstance());
            screeenShotBase = getScreenshot(result.getMethod().getMethodName(), driver);
//            extentTest.get().addScreenCaptureFromBase64String(screeenShotBase, "On " + (iteration_Count) + "th Fail " + result.getMethod().getMethodName());
            extentTest.get().addScreenCaptureFromBase64String(screeenShotBase, result.getMethod().getMethodName());


        } catch (Exception e) {  //Generic Exception for all type of exception
            e.printStackTrace();
        }

        extentTest.get().log(Status.INFO, "Test End Time: " + endTime);


        //-------------- To Log the Test Case Type and Package Name in the Report--------------------
        String packageName = result.getMethod().getRealClass().getPackage().getName();
        ITestContext context = result.getTestContext();
        case_Type = (String) context.getAttribute("case_Type");
        extentTest.get().log(Status.INFO, case_Type + "=>" + packageName);


        //----------------For Logging Test Scenario and Failure Logs--------------------------------

        String testScenarioName = (String) context.getAttribute("testScenarioName");
        extentTest.get().log(Status.INFO, "Test Scenario Name: " + testScenarioName);
        extentTest.get().log(Status.FAIL, result.getThrowable());

        //----------------For Changing the Test Name--------------------------------
        String testName = (String) context.getAttribute("testName");
        test.getModel().setName(testName);


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LocalTime currentTime = LocalTime.now();
        int iteration_Count = result.getMethod().getCurrentInvocationCount();
        String screeenShotBase;

        //-------- Format the time to HH:MM:SS-----------
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String endTime = currentTime.format(formatter);

        //---------Logging Screenshot Where Test Skipped and Test End Time--------------------------------

        try {
            //In below method we are getting test class of testNG->real class of test-> then getting driver field from the class
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")  //Method to get the driver life from the test
                    .get(result.getInstance());
            screeenShotBase = getScreenshot(result.getMethod().getMethodName(), driver);
            extentTest.get().addScreenCaptureFromBase64String(screeenShotBase,  result.getMethod().getMethodName());


        } catch (Exception e) {  //Generic Exception for all type of exception
            e.printStackTrace();
        }
        extentTest.get().log(Status.INFO, "Test End Time: " + endTime);


        //-------------- To Log the Test Case Type and Package Name in the Report-------------------
        String packageName = result.getMethod().getRealClass().getPackage().getName();
        ITestContext context = result.getTestContext();
        case_Type = (String) context.getAttribute("case_Type");
        extentTest.get().log(Status.INFO, case_Type + "=>" + packageName);


        //----------------For Logging Test Scenario and Test Skip Logs--------------------------------

        String testScenarioName = (String) context.getAttribute("testScenarioName");
        extentTest.get().log(Status.INFO, "Test Scenario Name: " + testScenarioName);
        extentTest.get().skip(result.getThrowable());

        //----------------For Changing the Test Name--------------------------------
        String testName = (String) context.getAttribute("testName");
        test.getModel().setName(testName);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {


    }


    @Override
    public void onFinish(ITestContext context) {

        extentReports.getStats();
        extentReports.flush();

    }

}