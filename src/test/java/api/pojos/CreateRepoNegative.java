package api.pojos;

import java.util.HashMap;
import java.util.List;

/**
 * This class represents the response for a failed repository creation attempt.
 * It includes fields for error messages, documentation URL, and status.
 */
public class CreateRepoNegative {

	/**
	 * The error message returned by the API.
	 */
	private String message;

	/**
	 * A list of errors with additional details.
	 */
	private List<HashMap<String, String>> errors;

	/**
	 * The URL to the API documentation for the error.
	 */
	private String documentation_url;

	/**
	 * The status of the API response.
	 */
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