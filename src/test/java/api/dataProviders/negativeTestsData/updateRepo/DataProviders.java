package api.dataProviders.negativeTestsData.updateRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

/**
 * This class provides data providers for negative test cases related to updating repositories.
 * It reads test data from JSON files and supplies it to test methods.
 */
public class DataProviders {

	/**
	 * Provides test data for updating a repository without providing a name.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "updateRepoWithoutNameProvider")
	public Object[][] updateRepoWithoutName() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/updateRepo/updateRepoWithoutName.json");
	}

	/**
	 * Provides test data for updating a non-existent repository.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "updateNonExistentRepoProvider")
	public Object[][] updateNonExistentRepo() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/updateRepo/updateNonExistentRepo.json");
	}
}