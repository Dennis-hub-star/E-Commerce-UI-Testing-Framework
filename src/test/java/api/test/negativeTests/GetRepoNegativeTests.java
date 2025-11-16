package api.test.negativeTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.base.BaseTest;
import api.dataProviders.negativeTestsData.getRepo.DataProviders;
import api.endpoints.UserEndPoints;
import io.restassured.response.Response;

public class GetRepoNegativeTests extends BaseTest {

	@Test(dataProvider = "getRepoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void getRepoWithWithoutNameParam(HashMap<String, String> input) throws IOException {

		Response response = UserEndPoints.getRepo(input.get("name"));
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(input.get("statusCode")));
		Assert.assertEquals(response.jsonPath().getString("message"), input.get("message"));
	}

	@Test(dataProvider = "getNonExistentRepoProvider", dataProviderClass = DataProviders.class)
	public void getNonExistantRepo(HashMap<String, String> input) throws IOException {

		Response response = UserEndPoints.getRepo(input.get("name"));
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(input.get("statusCode")));
		Assert.assertEquals(response.jsonPath().getString("message"), input.get("message"));

	}
}
