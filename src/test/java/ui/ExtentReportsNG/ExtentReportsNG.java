package ui.ExtentReportsNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentReportsNG class configures and provides an ExtentReports instance.
 * It is used for generating detailed test execution reports.
 */
public class ExtentReportsNG {
	
	/**
	 * Configures and returns an ExtentReports instance.
	 * 
	 * @return ExtentReports instance for reporting.
	 */
	public static ExtentReports ExtentReportsObject() {

		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);

		return extent;
	}

}