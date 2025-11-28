package api.test.negativeTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataProviders.negativeTestsData.updateRepo.DataProviders;
import api.endpoints.UserEndPoints;
import api.payload.CreateRepoPayload;
import api.pojos.CreateRepo;
import api.pojos.GetUpdateDeleteNegative;

/**
 * This class contains negative test cases for updating GitHub repositories.
 * It validates error responses for scenarios such as updating non-existent repositories
 * and attempting to update a repository without providing a name.
 */
public class UpdateRepoNegativeTest {

	/**
	 * Tests the API response when attempting to update a non-existent repository.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "updateNonExistentRepoProvider", dataProviderClass = DataProviders.class)
	public void updateRepoWithNonExistentName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.updateRepoDescription(input);

		GetUpdateDeleteNegative res = UserEndPoints.updateRepo(payload).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getMessage(), input.get("message"));

	}

	/**
	 * Tests the API response when attempting to update a repository without providing a name.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "updateRepoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void updateRepoWithEmptyName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.updateRepoDescription(input);

		GetUpdateDeleteNegative res = UserEndPoints.updateRepo(payload).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getMessage(), input.get("message"));
		Assert.assertEquals(res.getStatus(), input.get("statusCode"));
	}
}