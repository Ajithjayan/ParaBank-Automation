package base;

import Resource.ExtentReportedNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners  implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReportedNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        ITestListener.super.onTestFailure(result);
        extentTest.get().fail(result.getThrowable());

        try {
            WebDriver driver = DriverFactory.getDriver(); // ✅ get from ThreadLocal directly
            if (driver != null) {
                String filepath = new BaseTest().getScreenShot(
                        result.getMethod().getMethodName(), driver
                );
                extentTest.get().addScreenCaptureFromPath(filepath,
                        result.getMethod().getMethodName());
            }
        } catch (Exception e) {
            extentTest.get().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }


    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }
}
