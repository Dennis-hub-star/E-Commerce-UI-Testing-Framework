package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class RegistrationPage extends UiUtilities {

	WebDriver driver;
	private boolean isUserRegistered;

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

	public boolean isAcceptPolicy() {
		return acceptPolicy;
	}

//	public RegistrationSuccessPage register(String firstName, String lastName, String email, String telephone,
//			String password, String confirmPassword, String subscriptionStatus, boolean agreePrivacyPolicy) {
//		
//	}

	public void verifyThatUserIsNotRegistered(String expectedMessage) {
		isUserRegistered = false;
		
		if(isAcceptPolicy()) {
			WebElement messageEl = waitForVisibilityOfElementLocatedBy(errorMessages);
			verifyText(messageEl.getText(), expectedMessage);

		}

//		verifyText(alertEl.getText(), expectedAlert);

		if (!isAcceptPolicy()) {
			WebElement alertEl = waitForVisibilityOfElementLocatedBy(errorAlert);

			verifyText(alertEl.getText(), expectedMessage);

			System.out.println("************** Is user policy accepted: " + isAcceptPolicy());
		}

	}
}
