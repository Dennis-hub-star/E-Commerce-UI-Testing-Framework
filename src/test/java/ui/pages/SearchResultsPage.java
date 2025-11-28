package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Search Results Page of the application.
 * Provides methods to filter, verify, and interact with search results.
 */
public class SearchResultsPage extends UiUtilities {

	WebDriver driver;
	String productName;
	List<WebElement> productNames;
	List<WebElement> productPrices;

	/**
	 * Constructor to initialize the SearchResultsPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	public SearchResultsPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@for = 'mz-fss-0--1']")
	private WebElement displayInstockFileterCheckbox;

	By productImage = By.xpath("//*[@class='product-thumb']/div[1]");

	By noProductsFoundMessage = By.cssSelector("#entry_212469 p");
	@FindBy(xpath = "//*[@id = 'mz-fss-0-5']/following-sibling::label")
	WebElement displayOutOfStockFileterCheckbox;

	@FindBy(className = "btn-cart")
	WebElement addToCartHoverBtn;

	By productAddedToCartPopup = By.xpath("//*[@id = 'notification-box-top']/div");

	@FindBy(xpath = "//div[@class ='d-flex mb-3 align-items-start']/p")
	WebElement productAddedToCartMessage;

	/**
	 * Sets the product name to be searched.
	 * 
	 * @param product The product name to search for.
	 * @throws InterruptedException If interrupted during execution.
	 */
	public void setSearchedItem(String product) throws InterruptedException {

		this.productName = product;

	}

	/**
	 * Filters the search results to display only in-stock products.
	 * 
	 * @param availability The availability status (e.g., "In Stock").
	 * @throws InterruptedException If interrupted during execution.
	 */
	public void displayOnlyInStockProducts(String availability) throws InterruptedException {

		if (availability.equalsIgnoreCase("In Stock")) {
			displayInstockFileterCheckbox.click();
		}
		getElementsAfterSearch();
	}

	/**
	 * Filters the search results to display only out-of-stock products.
	 * 
	 * @param availability The availability status (e.g., "Out of Stock").
	 * @throws InterruptedException If interrupted during execution.
	 */
	public void displayOnlyOutOfStockProducts(String availability) throws InterruptedException {

		if (availability.equalsIgnoreCase("Out of Stock")) {
			displayOutOfStockFileterCheckbox.click();
		}
		getElementsAfterSearch();
	}

	/**
	 * Retrieves product names and prices after applying filters.
	 * 
	 * @throws InterruptedException If interrupted during execution.
	 */
	private void getElementsAfterSearch() throws InterruptedException {
		By productNameLocator = By.xpath("//*[@class='product-thumb']/div[2]/h4/a");
		By productPriceLocator = By.xpath("//*[@class='product-thumb']/div[2]/div/span");

		waitForStalenessThenVisibilityOfElementLocated(productPriceLocator);
		List<WebElement> prodNames = getElements(productNameLocator);
		List<WebElement> prodPrices = getElements(productPriceLocator);

		productNames = prodNames;
		productPrices = prodPrices;

	}

	/**
	 * Verifies that the searched product is present in the results.
	 * 
	 * @throws InterruptedException If interrupted during execution.
	 */
	public void verifyThatProductIsInResults() throws InterruptedException {

		productNames.forEach(name -> {
			assertTrue(name.getText().toLowerCase().contains(productName.toLowerCase()));
		});
	}

	/**
	 * Adds the first product in the search results to the cart.
	 * 
	 * @return CartPage object representing the cart page.
	 */
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

	/**
	 * Verifies that the product was successfully added to the cart.
	 */
	public void verifyThatProductIsAddedToCart() {
		waitForVisibilityOfElementLocatedBy(productAddedToCartPopup);

		String message = productAddedToCartMessage.getText();

		assertTrue(message.contains("Success: You have added"));

		waitForInvisibilityOfElementLocatedBy(productAddedToCartPopup);

	}

	/**
	 * Verifies that no products were found in the search results.
	 * 
	 * @param expectedMessage The expected message when no products are found.
	 */
	public void verifyThatNoProductWasFound(String expectedMessage) {
		WebElement messageEl = waitForVisibilityOfElementLocatedBy(noProductsFoundMessage);
		String actualMessage = messageEl.getText();
		assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
	}

}