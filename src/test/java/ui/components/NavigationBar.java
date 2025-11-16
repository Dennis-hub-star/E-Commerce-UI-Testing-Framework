package ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.pages.LoginPage;
import ui.pages.OrderHistoryPage;
import ui.pages.RegistrationPage;
import ui.pages.SearchResultsPage;
import ui.utils.UiUtilities;

public class NavigationBar extends UiUtilities {

	WebDriver driver;

	public NavigationBar(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "entry_217824")
	private WebElement wishlistLink;

	@FindBy(id = "entry_217825")
	private WebElement cartLink;

	@FindBy(xpath = "//input[@placeholder='Search For Products']")
	private WebElement searchField;

	@FindBy(xpath = "//*[@class = 'search-button']")
	private WebElement searchButton;

//	@FindBy(css = ".dropdown-hoverable:last-child")
//	private WebElement myAccount;

	By myAccountDropdownOptions = By.cssSelector(".mz-sub-menu-96 li");

	public SearchResultsPage searchProduct(String productName) throws InterruptedException {

		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		searchResultsPage.setSearchedItem(productName);

		searchField.clear();
		searchField.sendKeys(productName);
		searchButton.click();

		return searchResultsPage;
	}

	public void goToWishlist() {
		waitForElementToBeVisible(wishlistLink).click();
	}

	public void goToCart() {
		waitForElementToBeVisible(cartLink).click();
	}

	public LoginPage goToLoginPage() {
		goToMyAccountOption("Login");
		return new LoginPage(driver);
	}

	public RegistrationPage goToRegistrationPage() {
		goToMyAccountOption("Register");

		return new RegistrationPage(driver);
	}

	private void goToMyAccountOption(String optionName) {

		WebElement myAccount = driver.findElement(By.cssSelector(".dropdown-hoverable:last-child"));
		actions().moveToElement(myAccount).perform();

		WebElement optionToClick = getElements(myAccountDropdownOptions).stream()
				.filter(option -> option.getText().equalsIgnoreCase(optionName)).findFirst()
				.orElseThrow(() -> new RuntimeException("Option " + optionName + " not found in My Account dropdown"));

		waitForElementToBeVisible(optionToClick).click();
	}

//	public OrderHistoryPage goToOderHistoryPage() {
//		
//		try {
//		goToMyAccountOption("My order");
//		return new OrderHistoryPage(driver);
//		} catch (Exception e) {
//			throw new RuntimeException("Could not navigate to Order History page: " + e.getMessage());
//		}
//	}

}
