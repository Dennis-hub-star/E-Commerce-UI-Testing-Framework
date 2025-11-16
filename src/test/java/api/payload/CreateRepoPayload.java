package api.payload;

import java.util.HashMap;

import api.pojos.CreateRepo;

public class CreateRepoPayload {

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

	public static CreateRepo updateRepoDescription(HashMap<String,String>input) {

		CreateRepo createRepo = new CreateRepo();
		
		String name = input.get("name");
		String description = input.get("description");
		
		createRepo.setName(name);
		createRepo.setDescription(description);
		return createRepo;
	}

}
