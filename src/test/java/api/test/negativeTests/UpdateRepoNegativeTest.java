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

public class UpdateRepoNegativeTest {

	@Test(dataProvider = "updateNonExistentRepoProvider", dataProviderClass = DataProviders.class)
	public void updateRepoWithNonExistentName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.updateRepoDescription(input);

//		Response reponse = UserEndPoints.updateRepo(payload);
		GetUpdateDeleteNegative res = UserEndPoints.updateRepo(payload).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getMessage(), input.get("message"));

	}

	@Test(dataProvider = "updateRepoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void updateRepoWithEmptyName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.updateRepoDescription(input);

//		Response reponse = UserEndPoints.updateRepo(payload);
		GetUpdateDeleteNegative res = UserEndPoints.updateRepo(payload).as(GetUpdateDeleteNegative.class);
		Assert.assertEquals(res.getMessage(), input.get("message"));
		Assert.assertEquals(res.getStatus(), input.get("statusCode"));
	}
}
