package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Registration Success Page of the application.
 * Provides methods to verify successful registration or actions.
 */
public class RegistrationSuccessPage extends UiUtilities {

	WebDriver driver;

	/**
	 * Constructor to initialize the RegistrationSuccessPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	RegistrationSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".page-title")
	private WebElement registrationSuccessMessage;

	/**
	 * Verifies that the user registration was successful.
	 */
	public void verifyThatUserRegistered() {

		WebElement messageEl = waitForElementToBeVisible(registrationSuccessMessage);

		verifyText(messageEl.getText(), "Your Account Has Been Created!");
	}

	/**
	 * Verifies that an action was successful based on the expected message.
	 * 
	 * @param expectedMessage The expected success message.
	 */
	public void verifyActionSuccess(String expectedMessage) {

		WebElement messageEl = waitForElementToBeVisible(registrationSuccessMessage);

		verifyText(messageEl.getText(), expectedMessage);
	}

}