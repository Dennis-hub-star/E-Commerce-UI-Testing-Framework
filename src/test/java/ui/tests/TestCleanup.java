package ui.tests;

import org.testng.annotations.AfterSuite;

import ui.utils.EmailUtils;
import ui.utils.ZipUtils;

public class TestCleanup {
	
    @AfterSuite
	public void zipAndEmailReport() throws Exception {
	// compress the report folder to Reports.zip
	ZipUtils.zipFolder(System.getProperty("user.dir")+"/reports", System.getProperty("user.dir")+"/Reports.zip");

	// send the zipped file via email
	EmailUtils.sendEmailWithAttachment(
	    "lekwapemamyala@gmail.com",
	    "Automation Test Report - 11/11/2025",
	    "Hi team,\n\nAttached is the latest automation test report.\n\nRegards",
	    "Reports.zip"
	);
	
	}

}
