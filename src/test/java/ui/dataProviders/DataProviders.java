package ui.dataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import ui.utils.UiUtilities;

public class DataProviders {

	@DataProvider(name = "validLoginData")
	public static Object[][] loginData() throws IOException {
		return UiUtilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/loginData.json");
	}

	@DataProvider(name = "validRegistrationData")
	public static Object[][] registrationData() throws IOException {
//		String path = System.getProperty("user.dir")
//				+ "/src/test/resources/data/positive_test_data/registrationData.json";
//		List<HashMap<String, String>> data = Utilities.getJsonDataToMap(path);
//		Object[][] prepared = new Object[data.size()][1];
//		for (int i = 0; i < data.size(); i++) {
//			prepared[i][0] = data.get(i);
//		}
		return UiUtilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/registrationData.json");

	}
	
	@DataProvider(name = "endToEndPurchaseData")
	public static Object[][] endToEndPurchaseData() throws IOException {
//		String path = System.getProperty("user.dir")
//				+ "/src/test/resources/data/positive_test_data/endToEndPurchaseData.json";
//		List<HashMap<String, String>> data = Utilities.getJsonDataToMap(path);
//		Object[][] prepared = new Object[data.size()][1];
//		for (int i = 0; i < data.size(); i++) {
//			prepared[i][0] = data.get(i);
//		}
		return UiUtilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/endToEndPurchaseData.json");
	}
	
	
	@DataProvider(name = "orderHistoryData")
	public static Object[][] orderHistoryData() throws IOException {
//		String path = System.getProperty("user.dir")
//				+ "/src/test/resources/data/positive_test_data/orderHistoryData.json";
//		List<HashMap<String, String>> data = Utilities.getJsonDataToMap(path);
//		Object[][] prepared = new Object[data.size()][1];
//		for (int i = 0; i < data.size(); i++) {
//			prepared[i][0] = data.get(i);
//		}
		return UiUtilities.getJsonDataFromFile("/src/test/resources/ui/data/positive_test_data/orderHistoryData.json");
	}

	// add more providers for cartData.json, checkoutData.json, etc.

}
