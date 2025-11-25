package api.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Utilities;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import ui.utils.UiUtilities;

public class APIUtilities {

	private static String file = "api.global";

	public static RequestSpecification requestSpecification() throws IOException {

		String token = Utilities.getGlobalValue("token", file);

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/vnd.github+json");
		headers.put("X-GitHub-Api-Version", "2022-11-28");
		headers.put("Authorization", "Bearer " + token);
		RequestSpecification builder = new RequestSpecBuilder()
				.setBaseUri(Utilities.getGlobalValue("base_url", file)).addHeaders(headers).build();

		return builder;

	}

	public static Map<String, String> getPathParams(String repoName) throws IOException {

		Map<String, String> pathParams = new HashMap<String, String>();
		pathParams.put("owner", Utilities.getGlobalValue("owner", "api.global")); 
		pathParams.put("repo", repoName); 
		return pathParams;
	}

	public static Object[][] getRepoNamesFromJson(String filePath) throws IOException {
		String path = System.getProperty("user.dir") + filePath;

		// Step 1: Get list of maps from JSON (reuse your existing method)
		List<HashMap<String, String>> data = UiUtilities.getJsonDataToMap(path);

		// Step 2: Prepare 2D Object array for TestNG
		Object[][] repoNames = new Object[data.size()][1];

		// Step 3: Extract only repo name for each entry
		for (int i = 0; i < data.size(); i++) {
			repoNames[i][0] = data.get(i).get("name"); // Only the "name" value
		}

		return repoNames;
	}
}
