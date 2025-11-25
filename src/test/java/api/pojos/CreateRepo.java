package api.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRepo {

	private String name;
	private String description;
	private String homepage;

	private boolean is_template;
	
	// This annotation maps the JSON property "private" to the Java field "_private"
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
