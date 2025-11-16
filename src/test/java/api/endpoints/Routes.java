package api.endpoints;

public class Routes {

	/*
	CreateRepo("/user/repos"), 
	GetRepo("/repos/{owner}/{repo}"),
	UpdateRepo("/repos/{owner}/{repo}"),
	DeleteRepo("/repos/{owner}/{repo}");
	*/
	private static String endpoint = "/repos/{owner}/{repo}";
	
	
	public static String base_url = "https://api.github.com";
	public static String createRepo = base_url + "/user/repos";
	public static String getRepo = base_url + endpoint;
	public static String updateRepo = base_url + endpoint;
	public static String deleteRepo = base_url + endpoint;
	
}
