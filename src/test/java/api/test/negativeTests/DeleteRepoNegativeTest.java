package api.test.negativeTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataProviders.negativeTestsData.deleteRepo.DataProviders;
import api.endpoints.UserEndPoints;
import api.pojos.GetUpdateDeleteNegative;

/**
 * This class contains negative test cases for deleting GitHub repositories.
 * It validates error responses for scenarios such as deleting non-existent repositories
 * and attempting to delete a repository without providing a name.
 */
public class DeleteRepoNegativeTest {

	/**
	 * Tests the API response when attempting to delete a non-existent repository.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "deleteNonExistentRepoProvider", dataProviderClass = DataProviders.class)
	public void deleteNonExistentRepo(HashMap<String, String> input) throws IOException {
		
		GetUpdateDeleteNegative res = UserEndPoints.deleteRepo(input.get("name")).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getStatus(), input.get("statusCode"));
		Assert.assertEquals(res.getMessage(), input.get("message"));
	}

	/**
	 * Tests the API response when attempting to delete a repository without providing a name.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "deleteRepoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void deleteRepoWithoutName(HashMap<String, String> input) throws IOException {
		
		GetUpdateDeleteNegative res = UserEndPoints.deleteRepo(input.get("name")).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getStatus(), input.get("statusCode"));
		Assert.assertEquals(res.getMessage(), input.get("message"));
	}
}