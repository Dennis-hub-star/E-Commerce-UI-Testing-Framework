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

/**
 * This class contains negative test cases for creating GitHub repositories.
 * It validates error responses for scenarios such as missing repository name,
 * creating an existing repository, and using excessively long repository names.
 */
public class CreateRepoNegativeTests {

	/**
	 * Tests the API response when attempting to create a repository without a name.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "repoWithoutNameProvider", dataProviderClass = DataProviders.class)
	public void createRepoWithoutName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.getPayload(input);
		CreateRepoNegative res = UserEndPoints.createRepo(payload).as(CreateRepoNegative.class);
		validateResponseBody(input, res);

	}

	/**
	 * Tests the API response when attempting to create a repository that already exists.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "createExistingRepoProvider", dataProviderClass = DataProviders.class)
	public void createExistingRepo(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.getPayload(input);
		CreateRepoNegative res = UserEndPoints.createRepo(payload).as(CreateRepoNegative.class);
		validateResponseBody(input, res);

	}

	/**
	 * Tests the API response when attempting to create a repository with a name that exceeds the allowed length.
	 * 
	 * @param input A HashMap containing test data for the repository.
	 * @throws IOException If an I/O error occurs.
	 */
	@Test(dataProvider = "createRepoWithLongNameProvider", dataProviderClass = DataProviders.class)
	public void createRepoWithLongName(HashMap<String, String> input) throws IOException {

		CreateRepo payload = CreateRepoPayload.getPayload(input);
		CreateRepoNegative res = UserEndPoints.createRepo(payload).as(CreateRepoNegative.class);
		validateResponseBody(input, res);

	}

	/**
	 * Validates the response body against expected values.
	 * 
	 * @param input A HashMap containing expected response values.
	 * @param res The actual response object to validate.
	 */
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