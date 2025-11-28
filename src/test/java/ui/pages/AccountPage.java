package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Account Page of the application.
 * Provides methods to navigate to order history and retrieve account-related elements.
 */
public class AccountPage extends UiUtilities {

	WebDriver driver;

	/**
	 * Constructor to initialize the AccountPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	AccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	@FindBy(css ="//*[@class = 'list-group list-group-item:nth-child(2)']")
//	private WebElement editMyAccount;

	@FindBy(css = ".col-lg-2:first-child")
	private WebElement viewOrderHistory;

	By editMyAccount = By.cssSelector(".list-group-item:nth-child(2)");

	/**
	 * Retrieves the locator for the "Edit My Account" element.
	 * 
	 * @return The By locator for the "Edit My Account" element.
	 */
	public By getEditMyAccountEl() {

		return editMyAccount;
	}

	/**
	 * Navigates to the Order History page.
	 * 
	 * @return OrderHistoryPage object representing the order history page.
	 */
	public OrderHistoryPage goToOrderHistory() {
		waitUntilElementIsClickable(viewOrderHistory).click();
		
		return new OrderHistoryPage(driver);
	}

}