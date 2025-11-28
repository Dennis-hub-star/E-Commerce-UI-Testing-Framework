package api.endpoints;

/**
 * This class defines the API endpoints for interacting with GitHub repositories.
 * It provides the base URL and specific endpoints for creating, retrieving,
 * updating, and deleting repositories.
 */
public class Routes {

	/**
	 * Base URL for the GitHub API.
	 */
	public static String base_url = "https://api.github.com";

	/**
	 * Endpoint for creating a new repository.
	 */
	public static String createRepo = base_url + "/user/repos";

	/**
	 * Endpoint for retrieving a repository by owner and name.
	 */
	public static String getRepo = base_url + "/repos/{owner}/{repo}";

	/**
	 * Endpoint for updating a repository by owner and name.
	 */
	public static String updateRepo = base_url + "/repos/{owner}/{repo}";

	/**
	 * Endpoint for deleting a repository by owner and name.
	 */
	public static String deleteRepo = base_url + "/repos/{owner}/{repo}";
}