package ui.pages;

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

//	@FindBy(id = "input-newsletter-yes")
//	private WebElement subscribeYesRadioButton;

	@FindBy(xpath = "//*[text() = 'Yes']")
	private WebElement subscribeYesRadioButton;

//	@FindBy(id = "input-newsletter-no")
//	private WebElement subscribeNoRadioButton;

	@FindBy(xpath = "//*[text() = 'No']")
	private WebElement subscribeNoRadioButton;

	@FindBy(xpath = "//*[@for = 'input-agree']")
	private WebElement privacyPolicyCheckbox;

	// @FindBy(css ="input[type='submit'][value='Continue']")

	@FindBy(xpath = "//input[@type = 'submit']")
	private WebElement continueButton;

	public RegistrationSuccessPage register(String firstName, String lastName, String email, String telephone,
			String password, String confirmPassword, String subscriptionStatus, boolean agreePrivacyPolicy) {

		boolean subscribe = subscriptionStatus.equalsIgnoreCase("yes") ? true : false;

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

}
