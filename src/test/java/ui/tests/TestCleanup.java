package ui.tests;

import org.testng.annotations.AfterSuite;

import ui.utils.EmailUtils;
import ui.utils.ZipUtils;

/**
 * This class contains cleanup tasks to be performed after the test suite execution.
 * It compresses the test reports into a zip file and emails the report to the team.
 */
public class TestCleanup {
	
	/**
	 * Method to compress the report folder into a zip file and send it via email.
	 * 
	 * @throws Exception If an error occurs during zipping or emailing the report.
	 */
    @AfterSuite
	public void zipAndEmailReport() throws Exception {
		// Compress the report folder to Reports.zip
		ZipUtils.zipFolder(System.getProperty("user.dir")+"/reports", System.getProperty("user.dir")+"/Reports.zip");

		// Send the zipped file via email
		EmailUtils.sendEmailWithAttachment(
		    "lekwapemamyala@gmail.com",
		    "Automation Test Report - 11/11/2025",
		    "Hi team,\n\nAttached is the latest automation test report.\n\nRegards",
		    "Reports.zip"
		);
	}
}