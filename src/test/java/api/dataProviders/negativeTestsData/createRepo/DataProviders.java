package api.dataProviders.negativeTestsData.createRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

public class DataProviders {
	@DataProvider(name = "repoWithoutNameProvider")
	public Object[][] createRepoWithoutName() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/createRepo/createRepoWithoutName.json");
	}

	@DataProvider(name = "createExistingRepoProvider")
	public Object[][] createExistingRepo() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/createRepo/createExistingRepo.json");
	}

	@DataProvider(name = "createRepoWithLongNameProvider")
	public Object[][] createRepoWithLongName() throws IOException {

		return Utilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/createRepo/createRepoWithLongName.json");
	}
}
