package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class AccountLogoutPage extends UiUtilities {
	
	AccountLogoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".page-title")
	private WebElement accountLogoutMessage;
	
	public void verifyThatUserLoggedOut() {
		verifyText(accountLogoutMessage.getText(), "Account Logout");
	}

}
