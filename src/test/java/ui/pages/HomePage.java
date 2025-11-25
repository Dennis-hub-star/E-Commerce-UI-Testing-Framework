package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class HomePage extends UiUtilities {

	WebDriver driver;

	LoginPage loginPage;
	RegistrationPage registrationPage;

	public HomePage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".dropdown-hoverable:last-child")
	private WebElement myAccount;

	By login = By.cssSelector(".mz-sub-menu-96 li:first-child");

	By register = By.cssSelector(".mz-sub-menu-96 li:last-child");

	public RegistrationPage goToRegistrationPage() {

		actions().moveToElement(myAccount).perform();

		waitForVisibilityOfElementLocatedBy(register).click();

		return new RegistrationPage(driver);

	}

	public LoginPage goToLoginPage() {

		actions().moveToElement(myAccount).perform();
		waitForVisibilityOfElementLocatedBy(login).click();

		return new LoginPage(driver);

	}

}
