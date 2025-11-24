package ui.tests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.NegativeTestDataProviders;
import ui.pages.RegistrationPage;
import ui.pages.RegistrationSuccessPage;

public class RegistrationNegativeTest extends BaseTest {
	
	@Test(dataProvider = "negativeRegistrationDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void testNegativeRegistration(HashMap<String, String> input) {
		
		boolean agreePrivacyPolicy = Boolean.parseBoolean(input.get("acceptPolicy"));
		String email = input.get("email");

		RegistrationPage registrationPage = navigationBar.goToRegistrationPage();
		RegistrationSuccessPage registrationSuccess = registrationPage.register(input.get("firstName"),
				input.get("lastName"), email, input.get("telephone"), input.get("password"),
				input.get("confirmPassword"), input.get("subscribeNewsletter"), agreePrivacyPolicy);

		registrationPage.verifyThatUserIsNotRegistered(input.get("expectedError"));
		
		// The data combination involving invalid phone number and valid password does not trigger an error message.
		// In fact the registration is successful in this case. Which is not expected.
		// Registration with a long password (> 20 characters) also succeeds. Which is not expected.
		// Registration with userName as digits only also succeeds. Which is not expected.
		// Registration with lastName as digits only also succeeds. Which is not expected.
		// Registration with a blank userName also succeeds. Which is not expected.
	}

}
