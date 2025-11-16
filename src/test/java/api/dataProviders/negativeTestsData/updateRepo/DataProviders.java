package api.dataProviders.negativeTestsData.updateRepo;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import ui.utils.UiUtilities;

public class DataProviders {

	@DataProvider(name = "updateRepoWithoutNameProvider")
	public Object[][] updateRepoWithoutName() throws IOException {

		return UiUtilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/updateRepo/updateRepoWithoutName.json");
	}
	
	@DataProvider(name = "updateNonExistentRepoProvider")
	public Object[][] updateNonExistentRepo() throws IOException {

		return UiUtilities
				.getJsonDataFromFile("/src/test/resources/api/testData/negativeTestData/updateRepo/updateNonExistentRepo.json");
	}
}
