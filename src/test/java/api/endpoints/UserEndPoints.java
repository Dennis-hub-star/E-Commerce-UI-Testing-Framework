package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.ITestContext;

import api.pojos.CreateRepo;
import api.utilities.APIUtilities;
import io.restassured.response.Response;

public class UserEndPoints extends APIUtilities {

	public static Response createRepo(CreateRepo payload) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).body(payload).when().post(Routes.createRepo)
				.then().log().all().extract().response();
		return res;

	}

	public static Response getRepo(String name) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).pathParams(getPathParams(name)).when()
				.get(Routes.getRepo).then().log().all().extract().response();

		return res;

	}

	public static Response updateRepo(CreateRepo payload) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).pathParams(getPathParams(payload.getName()))
				.body(payload).when().patch(Routes.updateRepo).then().log().all().extract().response();

		return res;

	}

	public static Response deleteRepo(String name) throws IOException {

		Response res = given().log().all().spec(requestSpecification()).pathParams(getPathParams(name)).when()
				.delete(Routes.deleteRepo).then().log().all().extract().response();

		return res;

	}

}
