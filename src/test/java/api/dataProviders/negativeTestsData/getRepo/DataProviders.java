package api.dataProviders.negativeTestsData.getRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

/**
 * This class provides data providers for negative test cases related to retrieving repositories.
 * It reads test data from JSON files and supplies it to test methods.
 */
public class DataProviders {

	/**
	 * Provides test data for retrieving a non-existent repository.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "getNonExistentRepoProvider")
	public Object[][] getNonExistingRepo() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/getRepo/getNonExistentRepo.json");
	}

	/**
	 * Provides test data for retrieving a repository without providing a name.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "getRepoWithoutNameProvider")
	public Object[][] getRepoWithoutName() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/getRepo/getRepoWithoutName.json");
	}
}