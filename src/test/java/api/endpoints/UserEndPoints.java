package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import api.pojos.CreateRepo;
import api.utilities.APIUtilities;
import io.restassured.response.Response;

/**
 * This class contains methods for interacting with GitHub repository endpoints.
 * It provides functionality to create, retrieve, update, and delete repositories
 * using REST API calls.
 */
public class UserEndPoints extends APIUtilities {

	/**
	 * Sends a POST request to create a new repository.
	 * 
	 * @param payload The payload containing repository details.
	 * @return The response from the API.
	 * @throws IOException If an I/O error occurs.
	 */
	public static Response createRepo(CreateRepo payload) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).body(payload).when().post(Routes.createRepo)
				.then().log().all().extract().response();
		return res;

	}

	/**
	 * Sends a GET request to retrieve a repository by name.
	 * 
	 * @param name The name of the repository to retrieve.
	 * @return The response from the API.
	 * @throws IOException If an I/O error occurs.
	 */
	public static Response getRepo(String name) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).pathParams(getPathParams(name)).when()
				.get(Routes.getRepo).then().log().all().extract().response();

		return res;

	}

	/**
	 * Sends a PATCH request to update a repository.
	 * 
	 * @param payload The payload containing updated repository details.
	 * @return The response from the API.
	 * @throws IOException If an I/O error occurs.
	 */
	public static Response updateRepo(CreateRepo payload) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).pathParams(getPathParams(payload.getName()))
				.body(payload).when().patch(Routes.updateRepo).then().log().all().extract().response();

		return res;

	}

	/**
	 * Sends a DELETE request to delete a repository by name.
	 * 
	 * @param name The name of the repository to delete.
	 * @return The response from the API.
	 * @throws IOException If an I/O error occurs.
	 */
	public static Response deleteRepo(String name) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).pathParams(getPathParams(name)).when()
				.delete(Routes.deleteRepo).then().log().all().extract().response();

		return res;

	}

}