package api.test.positiveTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataProviders.positiveTestsData.DataProviders;
import api.endpoints.UserEndPoints;
import api.payload.CreateRepoPayload;
import api.pojos.CreateRepo;
import io.restassured.response.Response;

public class PositiveE2ETests {

	@Test(dataProvider = "createRepoData", dataProviderClass = DataProviders.class, priority = 1)
	public void createRepoTest(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.getPayload(input);
		Response response = UserEndPoints.createRepo(payload);
		CreateRepo res = response.as((CreateRepo.class));
		validateResponseBody(input, res, response, Integer.parseInt(input.get("statusCode")));

	}

	@Test(dataProvider = "createRepoData", dataProviderClass = DataProviders.class, priority = 2)
	public void getRepoTest(HashMap<String, String> input) throws IOException {

		Response response = UserEndPoints.getRepo(input.get("name"));
		CreateRepo res = response.as(CreateRepo.class);
		validateResponseBody(input, res, response, 200);

	}

	@Test(dataProvider = "updateRepoData", dataProviderClass = DataProviders.class, priority = 3)
	public void updateRepoTest(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.updateRepoDescription(input);
		Response response = UserEndPoints.updateRepo(payload);
		CreateRepo res = response.as(CreateRepo.class);
		validateResponseBody(input, res, response, 200);
	}

	@Test(dataProvider = "repoNamesProvider", dataProviderClass = DataProviders.class, priority = 4)
	public void deleteRepoTest(String name) throws IOException {

		Response res = UserEndPoints.deleteRepo(name);
		Assert.assertEquals(res.getStatusCode(), 204);

	}

	public void validateResponseBody(HashMap<String, String> input, CreateRepo res, Response response,
			int expectedStatusCode) {

		Assert.assertEquals(res.getName(), input.get("name"));
		Assert.assertEquals(res.getDescription(), input.get("description"));
		Assert.assertEquals(res.getHomepage(), input.get("homepage"));
		Assert.assertEquals(res.get_private(), Boolean.parseBoolean(input.get("private")));
		Assert.assertEquals(res.getIs_template(), Boolean.parseBoolean(input.get("is_template")));
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);

	}

}
