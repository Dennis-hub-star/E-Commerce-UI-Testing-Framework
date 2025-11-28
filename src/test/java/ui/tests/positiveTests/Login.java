package ui.tests.positiveTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.DataProviders;
import ui.pages.LoginPage;

/**
 * This class contains positive test cases for the Login functionality.
 * It verifies that a user can successfully log in and log out of the application.
 */
public class Login extends BaseTest{

	/**
	 * Test method to verify successful login and logout functionality.
	 * 
	 * @param input A HashMap containing test data such as email and password.
	 */
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void testLogin(HashMap<String, String> input) {

		// Navigate to the login page
		LoginPage userLogin = navigationBar.goToLoginPage();
		
		// Perform login with provided credentials
		userLogin.login(input.get("email"), input.get("password"));
		
		// Verify that the user is logged in
		userLogin.verifyThatUserLoggedIn();
		
		// Perform logout
		userLogin.userLogout();
		
		// Verify that the user is logged out
		userLogin.verifyThatUserLoggedOut();
	}
}