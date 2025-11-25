package ui.tests.negativeTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.NegativeTestDataProviders;
import ui.pages.LoginPage;

public class LoginNegativeTest extends BaseTest {

	@Test(dataProvider = "negativeLoginDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void loginNegativeTest(HashMap<String, String> input) {

		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		userLogin.verifyThatUserLoginFailed(input.get("expectedAlert"));

	}

}
