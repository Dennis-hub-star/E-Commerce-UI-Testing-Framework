package api.dataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import api.utilities.APIUtilities;
import ui.utils.UiUtilities;

public class DataProviders extends APIUtilities{
	
	@DataProvider(name = "createRepoData")
	public static Object[][] createRepo() throws IOException {
		return UiUtilities.getJsonDataFromFile("/src/test/resources/api/testData/createRepoData.json");
	}
	
	@DataProvider(name = "updateRepoData")
	public static Object[][] updateRepo() throws IOException {
		return UiUtilities.getJsonDataFromFile("/src/test/resources/api/testData/updateRepoData.json");
	}
	
	
	@DataProvider(name = "repoNamesProvider")
	public static Object[][] repoNamesProvider() throws IOException {
	    return getRepoNamesFromJson("/src/test/resources/api/testData/updateRepoData.json");
	}
	
	

}
