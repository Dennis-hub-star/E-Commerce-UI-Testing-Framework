package api.dataProviders.negativeTestsData.deleteRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

/**
 * This class provides data providers for negative test cases related to deleting repositories.
 * It reads test data from JSON files and supplies it to test methods.
 */
public class DataProviders {

	/**
	 * Provides test data for deleting a non-existent repository.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "deleteNonExistentRepoProvider")
	public Object[][] deleteNonExistentRepo() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/deleteRepo/deleteNonExistentRepo.json");
	}

	/**
	 * Provides test data for deleting a repository without providing a name.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "deleteRepoWithoutNameProvider")
	public Object[][] deleteRepoWithoutName() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/deleteRepo/deleteRepoWithoutName.json");
	}
}