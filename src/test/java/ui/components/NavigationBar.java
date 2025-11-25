package ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.pages.LoginPage;
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

	By wishlistLink = By.id("entry_217824");

	By cartLink = By.id("entry_217825");

	@FindBy(xpath = "//input[@placeholder='Search For Products']")
	private WebElement searchField;

	@FindBy(xpath = "//*[@class = 'search-button']")
	private WebElement searchButton;

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
		waitForVisibilityOfElementLocatedBy(wishlistLink).click();
	}

	public void goToCart() {
		waitForVisibilityOfElementLocatedBy(cartLink).click();
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

		WebElement myAccount = waitForVisibilityOfElementLocatedBy(By.cssSelector(".dropdown-hoverable:last-child"));
		actions().moveToElement(myAccount).perform();

		WebElement optionToClick = getElements(myAccountDropdownOptions).stream()
				.filter(option -> option.getText().equalsIgnoreCase(optionName)).findFirst()
				.orElseThrow(() -> new RuntimeException("Option " + optionName + " not found in My Account dropdown"));

		waitUntilElementIsClickable(optionToClick).click();
	}

}
