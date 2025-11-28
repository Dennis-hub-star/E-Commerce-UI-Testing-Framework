package api.test.negativeTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataProviders.negativeTestsData.getRepo.DataProviders;
import api.endpoints.UserEndPoints;
import io.restassured.response.Response;

/**
 * This class contains negative test cases for retrieving GitHub repositories.
 * It validates error responses for scenarios such as missing repository name
 * and attempting to retrieve non-existent repositories.
 */
public class GetRepoNegativeTests {

	/**
	 * Tests the API response when attempting to retrieve a repository without providing a name.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "getRepoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void getRepoWithWithoutNameParam(HashMap<String, String> input) throws IOException {

		Response response = UserEndPoints.getRepo(input.get("name"));
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(input.get("statusCode")));
		Assert.assertEquals(response.jsonPath().getString("message"), input.get("message"));
	}

	/**
	 * Tests the API response when attempting to retrieve a non-existent repository.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "getNonExistentRepoProvider", dataProviderClass = DataProviders.class)
	public void getNonExistantRepo(HashMap<String, String> input) throws IOException {

		Response response = UserEndPoints.getRepo(input.get("name"));
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(input.get("statusCode")));
		Assert.assertEquals(response.jsonPath().getString("message"), input.get("message"));

	}
}