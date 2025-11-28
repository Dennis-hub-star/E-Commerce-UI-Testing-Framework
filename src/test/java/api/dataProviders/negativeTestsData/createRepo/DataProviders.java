package api.dataProviders.negativeTestsData.createRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

/**
 * This class provides data providers for negative test cases related to creating repositories.
 * It reads test data from JSON files and supplies it to test methods.
 */
public class DataProviders {
	/**
	 * Provides test data for creating a repository without a name.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "repoWithoutNameProvider")
	public Object[][] createRepoWithoutName() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/createRepo/createRepoWithoutName.json");
	}

	/**
	 * Provides test data for creating a repository that already exists.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "createExistingRepoProvider")
	public Object[][] createExistingRepo() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/createRepo/createExistingRepo.json");
	}

	/**
	 * Provides test data for creating a repository with a name that exceeds the allowed length.
	 * 
	 * @return A 2D Object array containing test data for this scenario.
	 * @throws IOException If an error occurs while reading the JSON file.
	 */
	@DataProvider(name = "createRepoWithLongNameProvider")
	public Object[][] createRepoWithLongName() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/createRepo/createRepoWithLongName.json");
	}
}