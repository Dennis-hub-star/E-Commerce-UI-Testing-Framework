package api.pojos;

import java.util.HashMap;
import java.util.List;

public class CreateRepoNegative {

	private String message;
	private List<HashMap<String, String>> errors;
	private String documentation_url;
	private String status;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<HashMap<String, String>> getErrors() {
		return errors;
	}
	public void setErrors(List<HashMap<String, String>> errors) {
		this.errors = errors;
	}
	public String getDocumentation_url() {
		return documentation_url;
	}
	public void setDocumentation_url(String documentation_url) {
		this.documentation_url = documentation_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
