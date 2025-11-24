package api.dataProviders.negativeTestsData.getRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import common.Utilities;

public class DataProviders {

	@DataProvider(name = "getNonExistentRepoProvider")
	public Object[][] getNonExistingRepo() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/getRepo/getNonExistentRepo.json");
	}

	@DataProvider(name = "getRepoWithoutNameProvider")
	public Object[][] getRepoWithoutName() throws IOException {

		return Utilities.getJsonDataFromFile(
				"/src/test/resources/api/testData/negativeTestData/getRepo/getRepoWithoutName.json");
	}
}
