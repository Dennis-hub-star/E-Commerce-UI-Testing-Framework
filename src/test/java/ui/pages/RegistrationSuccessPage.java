package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class RegistrationSuccessPage extends UiUtilities {

	WebDriver driver;

	RegistrationSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".page-title")
	private WebElement registrationSuccessMessage;

	public void verifyThatUserRegistered() {

		WebElement messageEl = waitForElementToBeVisible(registrationSuccessMessage);

		verifyText(messageEl.getText(), "Your Account Has Been Created!");
	}

	public void verifyActionSuccess(String expectedMessage) {

		WebElement messageEl = waitForElementToBeVisible(registrationSuccessMessage);

		verifyText(messageEl.getText(), expectedMessage);
	}

}
