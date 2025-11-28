package ui.dataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

/**
 * DataProviders class provides test data for various test cases.
 * It reads data from JSON files and supplies it to the test methods.
 */
public class DataProviders {

    /**
     * Provides valid login data for positive test cases.
     * 
     * @return A 2D array of login data.
     * @throws IOException If there is an error reading the JSON file.
     */
	@DataProvider(name = "validLoginData")
	public static Object[][] loginData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/loginData.json");
	}

    /**
     * Provides valid registration data for positive test cases.
     * 
     * @return A 2D array of registration data.
     * @throws IOException If there is an error reading the JSON file.
     */
	@DataProvider(name = "validRegistrationData")
	public static Object[][] registrationData() throws IOException {

		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/registrationData.json");

	}

    /**
     * Provides data for end-to-end purchase test cases.
     * 
     * @return A 2D array of purchase data.
     * @throws IOException If there is an error reading the JSON file.
     */
	@DataProvider(name = "endToEndPurchaseData")
	public static Object[][] endToEndPurchaseData() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/endToEndPurchaseData.json");
	}

    /**
     * Provides order history data for test cases.
     * 
     * @return A 2D array of order history data.
     * @throws IOException If there is an error reading the JSON file.
     */
	@DataProvider(name = "orderHistoryData")
	public static Object[][] orderHistoryData() throws IOException {

		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/orderHistoryData.json");
	}

}