package ui.dataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

public class NegativeTestDataProviders {
	
	@DataProvider(name = "negativeRegistrationDataProvider")
	public static Object[][] negativeRegistrationData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeRegistrationData.json");
	}
	
	@DataProvider(name = "negativeLoginDataProvider")
	public static Object[][] negativeLoginData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeLoginData.json");
	}
	
	@DataProvider(name = "negativeSearchDataProvider")
	public static Object[][] negativeSearchData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeSearchData.json");
	}
	
	@DataProvider(name = "addOutOfStockProductsToCartDataProvider")
	public static Object[][] addOutOfStockProductData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/addOutOfStockProductsToCart.json");
	}
	
	@DataProvider(name = "negativeProductQuantityUpdateDataProvider")
	public static Object[][] negativeProductQuantityUpdateData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeProductQuantityUpdate.json");
	}
	
	@DataProvider(name = "negativeCheckoutDataProvider")
	public static Object[][] negativeCheckoutData() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/ui/data/negative_test_data/negativeCheckoutData.json");
	}


}
