package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class AccountPage extends UiUtilities {

	WebDriver driver;

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

	public By getEditMyAccountEl() {

		return editMyAccount;
	}

	public OrderHistoryPage goToOrderHistory() {
		waitUntilElementIsClickable(viewOrderHistory).click();
		
		return new OrderHistoryPage(driver);
	}

}
