package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Registration Page of the application.
 * Provides methods to register a new user and validate registration errors.
 */
public class RegistrationPage extends UiUtilities {

	WebDriver driver;

	/**
	 * Constructor to initialize the RegistrationPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//*[text() = 'Yes']")
	private WebElement subscribeYesRadioButton;

	@FindBy(xpath = "//*[text() = 'No']")
	private WebElement subscribeNoRadioButton;

	@FindBy(xpath = "//*[@for = 'input-agree']")
	private WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//input[@type = 'submit']")
	private WebElement continueButton;

	By errorAlert = By.cssSelector(".alert.alert-danger");
	By errorMessages = By.cssSelector(".text-danger");

	private boolean acceptPolicy;

	/**
	 * Registers a new user with the provided details.
	 * 
	 * @param firstName User's first name.
	 * @param lastName User's last name.
	 * @param email User's email address.
	 * @param telephone User's telephone number.
	 * @param password User's password.
	 * @param confirmPassword Confirmation of the user's password.
	 * @param subscriptionStatus Subscription status ("yes" or "no").
	 * @param agreePrivacyPolicy Whether the user agrees to the privacy policy.
	 * @return RegistrationSuccessPage object representing the success page.
	 */
	public RegistrationSuccessPage register(String firstName, String lastName, String email, String telephone,
			String password, String confirmPassword, String subscriptionStatus, boolean agreePrivacyPolicy) {

		boolean subscribe = subscriptionStatus.equalsIgnoreCase("yes") ? true : false;
		acceptPolicy = agreePrivacyPolicy;

		waitForElementToBeVisible(firstNameField);
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);

		if (subscribe) {
			subscribeYesRadioButton.click();
		} else {
			subscribeNoRadioButton.click();
		}

		if (agreePrivacyPolicy) {
			privacyPolicyCheckbox.click();
		}
		continueButton.click();

		return new RegistrationSuccessPage(driver);

	}

	/**
	 * Checks if the user accepted the privacy policy.
	 * 
	 * @return True if the privacy policy was accepted, false otherwise.
	 */
	public boolean isAcceptPolicy() {
		return acceptPolicy;
	}

	/**
	 * Verifies that the user is not registered and validates the error message.
	 * 
	 * @param expectedMessage The expected error message.
	 */
	public void verifyThatUserIsNotRegistered(String expectedMessage) {

		if (isAcceptPolicy()) {
			WebElement messageEl = waitForVisibilityOfElementLocatedBy(errorMessages);
			verifyText(messageEl.getText(), expectedMessage);

		}

		if (!isAcceptPolicy()) {
			WebElement alertEl = waitForVisibilityOfElementLocatedBy(errorAlert);

			verifyText(alertEl.getText(), expectedMessage);

			System.out.println("************** Is user policy accepted: " + isAcceptPolicy());
		}

	}
}