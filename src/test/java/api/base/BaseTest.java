package api.base;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;

/**
 * This class serves as the base test class for API testing.
 * It provides common setup methods and utilities for test cases.
 */
public class BaseTest {

	/**
	 * Thread-local storage for storing repository payload data.
	 */
	private static ThreadLocal<HashMap<String, String>> payload = new ThreadLocal<>();

	/**
	 * Faker instance for generating random test data.
	 */
	private Faker faker = new Faker();

	/**
	 * Sets up repository data before each test method.
	 * This method generates random repository details and stores them in the thread-local payload.
	 */
	@BeforeMethod
	public void createRepoBeforeTest() {
		HashMap<String, String> repoData = new HashMap<>();

		repoData.put("name", "repo-" + faker.number().digits(5));
		repoData.put("description", faker.lorem().sentence());
		repoData.put("homepage", "https://example.com/" + faker.internet().slug());
		repoData.put("private", "false");
		repoData.put("is_template", "false");
		
		payload.set(repoData);
	}

	/**
	 * Retrieves the thread-local payload data.
	 * 
	 * @return The payload data as a ThreadLocal HashMap.
	 */
	public static ThreadLocal<HashMap<String, String>> getPayload() {
		return payload;
	}
}