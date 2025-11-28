package api.pojos;

/**
 * This class represents the response for failed GET, UPDATE, or DELETE operations on a repository.
 * It includes fields for error messages, documentation URL, and status.
 */
public class GetUpdateDeleteNegative {

	/**
	 * The error message returned by the API.
	 */
	private String message;

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