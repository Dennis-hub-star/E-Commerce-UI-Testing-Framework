package ui.tests.positiveTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.DataProviders;
import ui.pages.RegistrationPage;
import ui.pages.RegistrationSuccessPage;

/**
 * This class contains positive test cases for the Registration functionality.
 * It verifies that a user can successfully register on the application.
 */
public class Registration extends BaseTest{

	/**
	 * Test method to verify successful user registration.
	 * 
	 * @param input A HashMap containing test data such as user details and preferences.
	 */
	@Test(dataProvider = "validRegistrationData", dataProviderClass = DataProviders.class)
	public void testRegistration(HashMap<String, String> input) {

		// Parse the privacy policy agreement flag
		boolean agreePrivacyPolicy = Boolean.parseBoolean(input.get("agreePrivacyPolicy"));
		
		// Generate a unique email address for registration
		String email = input.get("email") + System.currentTimeMillis() + "@gmail.com";

		// Navigate to the registration page
		RegistrationPage registrationPage = navigationBar.goToRegistrationPage();
		
		// Perform registration with the provided details
		RegistrationSuccessPage registrationSuccess = registrationPage.register(input.get("firstName"),
				input.get("lastName"), email, input.get("telephone"), input.get("password"),
				input.get("confirmPassword"), input.get("subscribeNewsletter"), agreePrivacyPolicy);

		// Verify that the user is successfully registered
		registrationSuccess.verifyThatUserRegistered();
	}
}