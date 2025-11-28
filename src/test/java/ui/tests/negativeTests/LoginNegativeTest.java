package ui.tests.negativeTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.NegativeTestDataProviders;
import ui.pages.LoginPage;

/**
 * This class contains negative test cases for the Login functionality.
 * It verifies that invalid login attempts are handled correctly.
 */
public class LoginNegativeTest extends BaseTest {

	/**
	 * Test method to verify that login fails with invalid credentials.
	 * 
	 * @param input A HashMap containing test data such as email, password, and expected alert message.
	 */
	@Test(dataProvider = "negativeLoginDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void loginNegativeTest(HashMap<String, String> input) {

		// Navigate to the login page
		LoginPage userLogin = navigationBar.goToLoginPage();
		
		// Attempt to log in with invalid credentials
		userLogin.login(input.get("email"), input.get("password"));
		
		// Verify that the login attempt failed with the expected alert message
		userLogin.verifyThatUserLoginFailed(input.get("expectedAlert"));
	}
}