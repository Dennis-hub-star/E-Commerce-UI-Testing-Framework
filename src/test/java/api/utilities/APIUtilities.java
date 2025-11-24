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

	private static String filePath = System.getProperty("user.dir") + "/src/test/resources/api/global.properties";

	public static RequestSpecification requestSpecification() throws IOException {

		String token = Utilities.getGlobalVariables("token", filePath);

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/vnd.github+json");
		headers.put("X-GitHub-Api-Version", "2022-11-28");
		// "Authorization", "Bearer " + token
		headers.put("Authorization", "Bearer " + token);
		RequestSpecification builder = new RequestSpecBuilder()
				.setBaseUri(Utilities.getGlobalVariables("base_url", filePath)).addHeaders(headers).build();

		// System.out.println(getGlobalVariables("base_url"));
		return builder;

	}

	public static Map<String, String> getPathParams(String repoName) throws IOException {
		// Object context = repoName.getSuite().getAttribute("repoName");

		Map<String, String> pathParams = new HashMap<String, String>();
		pathParams.put("owner", Utilities.getGlobalVariables("owner", filePath)); // Replace with actual username
		pathParams.put("repo", repoName); // Replace with actual repository name

		System.out.println("THIS IS YOUR FIRST PATH PARAMETER:  " + pathParams.get("owner"));
		System.out.println("THIS IS YOUR SECOND PATH PARAMETER:  " + pathParams.get("repo"));
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
