package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.components.SideMenu;
import ui.utils.UiUtilities;

public class LoginPage extends UiUtilities {

	WebDriver driver;
	SideMenu sideMenu;

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

	By loginErrorMessage = By.cssSelector(".alert.alert-danger.alert-dismissible");

	public AccountPage login(String email, String password) {
		sideMenu = new SideMenu(driver);
		waitForElementToBeVisible(emailField).clear();
		emailField.sendKeys(email);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginButton.click();

//		return new SearchResultsPage(driver);
		return new AccountPage(driver);
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
	
	public void verifyThatUserLoggedIn() {
		verifyText(sideMenu.getButtonFromSideMenu("My Account").getText(), "My Account");
		verifyText(sideMenu.getButtonFromSideMenu("Edit Account").getText(), "Edit Account");
	}

	public void userLogout() {
		WebElement logoutBtn = sideMenu.getButtonFromSideMenu("Logout");

		logoutBtn.click();
	}

	public void verifyThatUserLoggedOut() {
		
		WebElement messageEl = waitForElementToBeVisible(accountLogoutMessage);
		verifyText(messageEl.getText(), "Account Logout");
	}
	


}