package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class SearchResultsPage extends UiUtilities {

	WebDriver driver;
	String productName;
	List<WebElement> productNames;
	List<WebElement> productPrices;

	public SearchResultsPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@placeholder='Search For Products']")
	private WebElement searchField; // ******************** delete

	@FindBy(xpath = "//*[@class = 'search-button']")
	private WebElement searchButton; // ******************** delete

	@FindBy(xpath = "//*[@for = 'mz-fss-0--1']")
	private WebElement displayInstockFileterCheckbox;// *********** DELETE

	By productImage = By.xpath("//*[@class='product-thumb']/div[1]");
	
	By noProductsFoundMessage = By.cssSelector("#entry_212469 p");
	//By outOfStockLabel = By.xpath("//*[@id = 'mz-fss-0-5']/following-sibling::label']");
	
	@FindBy(xpath = "//*[@id = 'mz-fss-0-5']/following-sibling::label")
	WebElement displayOutOfStockFileterCheckbox;

	@FindBy(className = "btn-cart")
	WebElement addToCartHoverBtn;


	
	By productAddedToCartPopup = By.xpath("//*[@id = 'notification-box-top']/div");

	@FindBy(xpath = "//div[@class ='d-flex mb-3 align-items-start']/p")
	WebElement productAddedToCartMessage;

	public void setSearchedItem(String product) throws InterruptedException {

		this.productName = product;

	}

	public void displayOnlyInStockProducts(String availability) throws InterruptedException {

		if (availability.equalsIgnoreCase("In Stock")) {
			displayInstockFileterCheckbox.click();
		}
		getElementsAfterSearch();
	}
	
	public void displayOnlyOutOfStockProducts(String availability) throws InterruptedException {

		if (availability.equalsIgnoreCase("Out of Stock")) {
			displayOutOfStockFileterCheckbox.click();
		}
		getElementsAfterSearch();
	}

	private void getElementsAfterSearch() throws InterruptedException {
		By productNameLocator = By.xpath("//*[@class='product-thumb']/div[2]/h4/a");
		By productPriceLocator = By.xpath("//*[@class='product-thumb']/div[2]/div/span");

		waitForStalenessThenVisibilityOfElementLocated(productPriceLocator);
		List<WebElement> prodNames = getElements(productNameLocator);
		List<WebElement> prodPrices = getElements(productPriceLocator);

		productNames = prodNames;
		productPrices = prodPrices;

	}

	public void verifyThatProductIsInResults() throws InterruptedException {

		productNames.forEach(name -> {
			assertTrue(name.getText().toLowerCase().contains(productName.toLowerCase()));
		});
	}

	public CartPage addProductToCart() {
		try {

			WebElement image = getElements(productImage).get(0);

			actions().moveToElement(image).perform();

			waitUntilElementIsClickable(addToCartHoverBtn);

			
			addToCartHoverBtn.click();


		} catch (Exception e) {
			System.out.println("Failed to add product to cart: " + e.getMessage());
		}

		return new CartPage(driver);
	}

	public void verifyThatProductIsAddedToCart() {
		waitForVisibilityOfElementLocatedBy(productAddedToCartPopup);

		String message = productAddedToCartMessage.getText();

		assertTrue(message.contains("Success: You have added"));

		waitForInvisibilityOfElementLocatedBy(productAddedToCartPopup);

	}
	
	
	public void verifyThatNoProductWasFound(String expectedMessage) {
		WebElement messageEl = waitForVisibilityOfElementLocatedBy(noProductsFoundMessage);
		String actualMessage = messageEl.getText();
		assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
	}
	
	
	

}