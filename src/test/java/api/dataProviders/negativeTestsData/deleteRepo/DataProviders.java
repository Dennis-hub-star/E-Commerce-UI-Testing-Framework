package api.dataProviders.negativeTestsData.deleteRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

public class DataProviders {

	@DataProvider(name = "deleteNonExistentRepoProvider")
	public Object[][] deleteNonExistentRepo() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/deleteRepo/deleteNonExistentRepo.json");
	}

	@DataProvider(name = "deleteRepoWithoutNameProvider")
	public Object[][] deleteRepoWithoutName() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/deleteRepo/deleteRepoWithoutName.json");
	}
}
