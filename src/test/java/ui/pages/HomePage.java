package ui.pages;

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
	private WebElement myAccount; // *********** delete

	@FindBy(css = ".mz-sub-menu-96 li:first-child")
	private WebElement login; // *********** delete

	@FindBy(css = ".mz-sub-menu-96 li:last-child")
	private WebElement register;  // *********** delete

	public RegistrationPage goToRegistrationPage() {

		actions().moveToElement(myAccount).perform();

		waitForElementToBeVisible(register).click();

		return new RegistrationPage(driver);

	}

//	public Utilities goToPage(String pageName) {
//
//		Map<String, Supplier<Utilities>> pageMap = Map.of("login", () -> new LoginPage(driver), "register",
//				() -> new RegistrationPage(driver));
//
//		WebElement elementToClick = pageName.equalsIgnoreCase("loginPage") ? login : register;
//		waitForElementToBeVisible(elementToClick);
//		elementToClick.click();
//
//		return pageMap.get(pageName.toLowerCase()).get(); // returns BasePage reference
//
//	}

	// ********* delete 
	public LoginPage goToLoginPage() {

		actions().moveToElement(myAccount).perform();
		waitForElementToBeVisible(login).click();

		return new LoginPage(driver);

	}

}
