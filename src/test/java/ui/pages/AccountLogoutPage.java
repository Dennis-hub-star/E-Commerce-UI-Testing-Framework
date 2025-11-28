package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Account Logout Page of the application.
 * Provides methods to verify that the user has successfully logged out.
 */
public class AccountLogoutPage extends UiUtilities {
	
	/**
	 * Constructor to initialize the AccountLogoutPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	AccountLogoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".page-title")
	private WebElement accountLogoutMessage;
	
	/**
	 * Verifies that the user has successfully logged out.
	 */
	public void verifyThatUserLoggedOut() {
		verifyText(accountLogoutMessage.getText(), "Account Logout");
	}

}