package api.base;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;

public class BaseTest {

	private static ThreadLocal<HashMap<String, String>> payload = new ThreadLocal<>();
	private Faker faker = new Faker();

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

	public static ThreadLocal<HashMap<String, String>> getPayload() {
		return payload;
	}
}
