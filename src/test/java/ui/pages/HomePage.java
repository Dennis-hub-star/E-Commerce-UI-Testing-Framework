package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Home Page of the application.
 * Provides methods to navigate to the login and registration pages.
 */
public class HomePage extends UiUtilities {

	WebDriver driver;

	LoginPage loginPage;
	RegistrationPage registrationPage;

	/**
	 * Constructor to initialize the HomePage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	public HomePage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".dropdown-hoverable:last-child")
	private WebElement myAccount;

	By login = By.cssSelector(".mz-sub-menu-96 li:first-child");

	By register = By.cssSelector(".mz-sub-menu-96 li:last-child");

	/**
	 * Navigates to the Registration Page.
	 * 
	 * @return RegistrationPage object representing the registration page.
	 */
	public RegistrationPage goToRegistrationPage() {

		actions().moveToElement(myAccount).perform();

		waitForVisibilityOfElementLocatedBy(register).click();

		return new RegistrationPage(driver);

	}

	/**
	 * Navigates to the Login Page.
	 * 
	 * @return LoginPage object representing the login page.
	 */
	public LoginPage goToLoginPage() {

		actions().moveToElement(myAccount).perform();
		waitForVisibilityOfElementLocatedBy(login).click();

		return new LoginPage(driver);

	}

}