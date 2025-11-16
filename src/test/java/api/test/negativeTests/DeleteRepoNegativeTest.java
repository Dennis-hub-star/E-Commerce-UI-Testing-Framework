package api.test.negativeTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataProviders.negativeTestsData.deleteRepo.DataProviders;
import api.endpoints.UserEndPoints;
import api.pojos.GetUpdateDeleteNegative;

public class DeleteRepoNegativeTest {

	@Test(dataProvider = "deleteNonExistentRepoProvider", dataProviderClass = DataProviders.class)
	public void deleteNonExistentRepo(HashMap<String, String> input) throws IOException {
		
		GetUpdateDeleteNegative res = UserEndPoints.deleteRepo(input.get("name")).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getStatus(), input.get("statusCode"));
		Assert.assertEquals(res.getMessage(), input.get("message"));
	}

	@Test(dataProvider = "deleteRepoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void deleteRepoWithoutName(HashMap<String, String> input) throws IOException {
		
		GetUpdateDeleteNegative res = UserEndPoints.deleteRepo(input.get("name")).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getStatus(), input.get("statusCode"));
		Assert.assertEquals(res.getMessage(), input.get("message"));
	}
}
