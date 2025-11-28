package ui.dataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

/**
 * This class provides data providers for negative test cases in the UI module.
 * It reads test data from JSON files and supplies it to test methods.
 */
public class NegativeTestDataProviders {
	
	/**
	 * Provides test data for negative registration scenarios.
	 * 
	 * @return A 2D Object array containing test data for registration tests.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "negativeRegistrationDataProvider")
	public static Object[][] negativeRegistrationData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeRegistrationData.json");
	}
	
	/**
	 * Provides test data for negative login scenarios.
	 * 
	 * @return A 2D Object array containing test data for login tests.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "negativeLoginDataProvider")
	public static Object[][] negativeLoginData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeLoginData.json");
	}
	
	/**
	 * Provides test data for negative product search scenarios.
	 * 
	 * @return A 2D Object array containing test data for search tests.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "negativeSearchDataProvider")
	public static Object[][] negativeSearchData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeSearchData.json");
	}
	
	/**
	 * Provides test data for adding out-of-stock products to the cart.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "addOutOfStockProductsToCartDataProvider")
	public static Object[][] addOutOfStockProductData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/addOutOfStockProductsToCart.json");
	}
	
	/**
	 * Provides test data for updating product quantities in the cart with invalid inputs.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "negativeProductQuantityUpdateDataProvider")
	public static Object[][] negativeProductQuantityUpdateData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeProductQuantityUpdate.json");
	}
	
	/**
	 * Provides test data for negative checkout scenarios.
	 * 
	 * @return A 2D Object array containing test data for checkout tests.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "negativeCheckoutDataProvider")
	public static Object[][] negativeCheckoutData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeCheckoutData.json");
	}


}