package ui.tests.negativeTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.NegativeTestDataProviders;
import ui.pages.RegistrationPage;
import ui.pages.RegistrationSuccessPage;

/**
 * This class contains negative test cases for the Registration functionality.
 * It verifies that invalid registration attempts are handled correctly.
 */
public class RegistrationNegativeTest extends BaseTest {
	
	/**
	 * Test method to verify that registration fails with invalid data.
	 * 
	 * @param input A HashMap containing test data such as user details and expected error messages.
	 */
	@Test(dataProvider = "negativeRegistrationDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void testNegativeRegistration(HashMap<String, String> input) {
		
		// Parse the privacy policy agreement flag
		boolean agreePrivacyPolicy = Boolean.parseBoolean(input.get("acceptPolicy"));
		
		// Retrieve the email address from the input data
		String email = input.get("email");

		// Navigate to the registration page
		RegistrationPage registrationPage = navigationBar.goToRegistrationPage();
		
		// Attempt to register with invalid data
		RegistrationSuccessPage registrationSuccess = registrationPage.register(input.get("firstName"),
				input.get("lastName"), email, input.get("telephone"), input.get("password"),
				input.get("confirmPassword"), input.get("subscribeNewsletter"), agreePrivacyPolicy);

		// Verify that the registration attempt failed with the expected error message
		registrationPage.verifyThatUserIsNotRegistered(input.get("expectedError"));
		
		// The data combination involving invalid phone number and valid password does not trigger an error message.
		// In fact the registration is successful in this case. Which is not expected.
		// Registration with a long password (> 20 characters) also succeeds. Which is not expected.
		// Registration with userName as digits only also succeeds. Which is not expected.
		// Registration with lastName as digits only also succeeds. Which is not expected.
		// Registration with a blank userName also succeeds. Which is not expected.
	}

}