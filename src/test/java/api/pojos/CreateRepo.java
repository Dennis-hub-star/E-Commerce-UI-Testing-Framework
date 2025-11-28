package api.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the payload for creating a GitHub repository.
 * It includes fields for repository details such as name, description, and visibility.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRepo {

	/**
	 * The name of the repository.
	 */
	private String name;

	/**
	 * The description of the repository.
	 */
	private String description;

	/**
	 * The homepage URL of the repository.
	 */
	private String homepage;

	/**
	 * Indicates whether the repository is a template.
	 */
	private boolean is_template;

	/**
	 * Indicates whether the repository is private.
	 */
	@JsonProperty("private")
	private boolean _private;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Boolean get_private() {
		return _private;
	}

	public void set_private(boolean _private) {
		this._private = _private;
	}

	public Boolean getIs_template() {
		return is_template;
	}

	public void setIs_template(boolean is_template) {
		this.is_template = is_template;
	}
}