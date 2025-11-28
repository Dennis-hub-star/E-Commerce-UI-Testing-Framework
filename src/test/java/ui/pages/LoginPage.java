package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.components.SideMenu;
import ui.utils.UiUtilities;

/**
 * Represents the Login Page of the application.
 * Provides methods to log in, verify login status, and handle logout actions.
 */
public class LoginPage extends UiUtilities {

	WebDriver driver;
	SideMenu sideMenu;

	/**
	 * Constructor to initialize the LoginPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@name = 'email']")
	private WebElement emailField;

	@FindBy(xpath = "//*[@name = 'password']")
	private WebElement passwordField;

	@FindBy(xpath = "//*[@value = 'Login']")
	private WebElement loginButton;

	@FindBy(css = ".page-title")
	private WebElement accountLogoutMessage;

//	By loginErrorMessage = By.cssSelector(".alert.alert-danger.alert-dismissible");
	By errorAlert = By.cssSelector(".alert.alert-danger");

	/**
	 * Logs in with the provided email and password.
	 * 
	 * @param email User's email address.
	 * @param password User's password.
	 * @return AccountPage object representing the account page after login.
	 */
	public AccountPage login(String email, String password) {
		sideMenu = new SideMenu(driver);
		waitForElementToBeVisible(emailField).clear();
		emailField.sendKeys(email);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginButton.click();

		return new AccountPage(driver);
	}
	
	/**
	 * Verifies that the user login failed and validates the error message.
	 * 
	 * @param alertMessage The expected error message.
	 */
	public void verifyThatUserLoginFailed(String alertMessage) {
		WebElement errorEl = waitForVisibilityOfElementLocatedBy(errorAlert);
		verifyText(errorEl.getText(), alertMessage);
	}
	


	/**
	 * Helper that logs in and immediately searches for a product and adds it to cart.
	 * This reduces repetition in tests that need to perform the full flow and keeps
	 * each test independent.
	 *
	 * @param email user email
	 * @param password user password
	 * @param product product name to search and add
	 * @return CartPage after product has been added
	 */
//	public CartPage loginAndAddProduct(String email, String password, String product) throws InterruptedException {
//		SearchResultsPage results = login(email, password);
//		return results.searchAndAddProduct(product);
//	}
	
	/**
	 * Verifies that the user is logged in by checking the side menu options.
	 */
	public void verifyThatUserLoggedIn() {
		verifyText(sideMenu.getButtonFromSideMenu("My Account").getText(), "My Account");
		verifyText(sideMenu.getButtonFromSideMenu("Edit Account").getText(), "Edit Account");
	}

	/**
	 * Logs out the user by clicking the logout button in the side menu.
	 */
	public void userLogout() {
		WebElement logoutBtn = sideMenu.getButtonFromSideMenu("Logout");

		logoutBtn.click();
	}

	/**
	 * Verifies that the user has successfully logged out.
	 */
	public void verifyThatUserLoggedOut() {
		
		WebElement messageEl = waitForElementToBeVisible(accountLogoutMessage);
		verifyText(messageEl.getText(), "Account Logout");
	}
	


}