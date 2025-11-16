package api.test.negativeTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataProviders.negativeTestsData.createRepo.DataProviders;
import api.endpoints.UserEndPoints;
import api.payload.CreateRepoPayload;
import api.pojos.CreateRepo;
import api.pojos.CreateRepoNegative;

public class CreateRepoNegativeTests {

	@Test(dataProvider = "repoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void createRepoWithoutName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.getPayload(input);
		CreateRepoNegative res = UserEndPoints.createRepo(payload).as(CreateRepoNegative.class);
		validateResponseBody(input, res);

	}

	@Test(dataProvider = "createExistingRepoProvider", dataProviderClass = DataProviders.class)

	public void createExistingRepo(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.getPayload(input);
		CreateRepoNegative res = UserEndPoints.createRepo(payload).as(CreateRepoNegative.class);
		validateResponseBody(input, res);

	}

	@Test(dataProvider = "createRepoWithLongNameProvider", dataProviderClass = DataProviders.class)
	public void createRepoWithLongName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.getPayload(input);
		CreateRepoNegative res = UserEndPoints.createRepo(payload).as(CreateRepoNegative.class);
		validateResponseBody(input, res);

	}

	private void validateResponseBody(HashMap<String, String> input, CreateRepoNegative res) {
		String expectedMessage = input.get("message");
		String expectedCode = input.get("code");
		String expectedField = input.get("field");
		String expectedStatusCode = input.get("statusCode");

		Assert.assertEquals(res.getMessage(), expectedMessage);
		Assert.assertEquals(res.getErrors().get(0).get("code"), expectedCode);
		Assert.assertEquals(res.getErrors().get(0).get("field"), expectedField);
		Assert.assertEquals(res.getStatus(), expectedStatusCode);
	}
}
