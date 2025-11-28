package api.dataProviders.positiveTestsData;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import api.utilities.APIUtilities;
import common.Utilities;

/**
 * This class provides data providers for positive test cases.
 * It reads test data from JSON files and supplies it to test methods.
 */
public class DataProviders extends APIUtilities {

	/**
	 * Provides test data for creating repositories.
	 * 
	 * @return A 2D Object array containing test data for repository creation.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "createRepoData")
	public static Object[][] createRepo() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/api/testData/createRepoData.json");
	}

	/**
	 * Provides test data for updating repositories.
	 * 
	 * @return A 2D Object array containing test data for repository updates.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "updateRepoData")
	public static Object[][] updateRepo() throws IOException {
		return Utilities.getJsonDataFromFile("/src/test/resources/api/testData/updateRepoData.json");
	}

	/**
	 * Provides repository names for test cases.
	 * 
	 * @return A 2D Object array containing repository names.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "repoNamesProvider")
	public static Object[][] repoNamesProvider() throws IOException {
		return getRepoNamesFromJson("/src/test/resources/api/testData/updateRepoData.json");
	}

}