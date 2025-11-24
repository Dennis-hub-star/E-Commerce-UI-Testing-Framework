package ui.dataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;
import ui.utils.UiUtilities;

public class DataProviders {

	@DataProvider(name = "validLoginData")
	public static Object[][] loginData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/loginData.json");
	}

	@DataProvider(name = "validRegistrationData")
	public static Object[][] registrationData() throws IOException {

		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/registrationData.json");

	}

	@DataProvider(name = "endToEndPurchaseData")
	public static Object[][] endToEndPurchaseData() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/endToEndPurchaseData.json");
	}

	@DataProvider(name = "orderHistoryData")
	public static Object[][] orderHistoryData() throws IOException {

		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/orderHistoryData.json");
	}

}
