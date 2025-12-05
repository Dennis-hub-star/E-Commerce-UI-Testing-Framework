package common;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ui.base.BaseTest;
import ui.utils.UiUtilities;

/**
 * Listeners class implements ITestListener to provide custom test execution behavior.
 * It integrates with ExtentReports for logging test results and capturing screenshots on failure.
 */
public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportsNG.ExtentReportsObject();
	ExtentTest test;

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	/**
	 * Logs the start of a test method.
	 * 
	 * @param result Test result object containing method details.
	 */
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);

		test = extent.createTest(result.getMethod().getMethodName());

		extentTest.set(test);
	}

	/**
	 * Logs the success of a test method.
	 * 
	 * @param result Test result object containing method details.
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		// ITestListener.super.onTestSuccess(result);
		ITestListener.super.onTestSuccess(result);

		extentTest.get().log(Status.PASS, "Test has passed");
	}

	/**
	 * Logs the failure of a test method and captures a screenshot.
	 * 
	 * @param result Test result object containing method details.
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);

		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {

			e.printStackTrace();
		}

		String filePath = null;

		try {
			filePath = UiUtilities.getScreenshot(result.getMethod().getMethodName(), driver);
			System.out.println("Screenshot saved at: " + filePath);

		
		} catch (IOException e) {

			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath);
	}

	/**
	 * Logs the skipping of a test method.
	 * 
	 * @param result Test result object containing method details.
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	/**
	 * Logs the completion of all test methods in a test context.
	 * 
	 * @param context Test context object containing details of the test run.
	 */
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}

}