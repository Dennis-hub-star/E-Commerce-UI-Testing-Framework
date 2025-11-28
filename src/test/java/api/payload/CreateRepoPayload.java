package api.payload;

import java.util.HashMap;

import api.pojos.CreateRepo;

/**
 * This class provides utility methods for creating payloads for repository operations.
 */
public class CreateRepoPayload {

	/**
	 * Generates a payload for creating a new repository.
	 * 
	 * @param input A HashMap containing repository details such as name, description, and visibility.
	 * @return A CreateRepo object populated with the provided details.
	 */
	public static CreateRepo getPayload(HashMap<String, String> input) {

		CreateRepo createRepo = new CreateRepo();

		String repoName = input.get("name");
		String repoDescription = input.get("description");
		String repoHomepage = input.get("homepage");
		String repoPrivate = input.get("private");
		String repoIsTemplate = input.get("is_template");

		createRepo.setName(repoName);
		createRepo.setDescription(repoDescription);
		createRepo.setHomepage(repoHomepage);
		createRepo.set_private(Boolean.parseBoolean(repoPrivate));
		createRepo.setIs_template(Boolean.parseBoolean(repoIsTemplate));

		return createRepo;

	}

	/**
	 * Generates a payload for updating the description of an existing repository.
	 * 
	 * @param input A HashMap containing the repository name and new description.
	 * @return A CreateRepo object with updated description details.
	 */
	public static CreateRepo updateRepoDescription(HashMap<String, String> input) {

		CreateRepo createRepo = new CreateRepo();
		
		String name = input.get("name");
		String description = input.get("description");
		
		createRepo.setName(name);
		createRepo.setDescription(description);
		return createRepo;
	}

}