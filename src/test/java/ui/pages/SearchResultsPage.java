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

	@FindBy(className = "btn-cart")
	WebElement addToCartHoverBtn;

	@FindBy(xpath = "//*[@id = 'notification-box-top']/div")
	WebElement productAddedToCartPopup;

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

	private void getElementsAfterSearch() throws InterruptedException {
		By productNameLocator = By.xpath("//*[@class='product-thumb']/div[2]/h4/a");
		By productPriceLocator = By.xpath("//*[@class='product-thumb']/div[2]/div/span");

//		Thread.sleep(2000);
		waitForElementLocatedBy(productPriceLocator);
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
			// Locate the first product image

			WebElement image = getElements(productImage).get(0);

			// Hover over the product image
			actions().moveToElement(image).perform();

			// Wait for the "Add to Cart" button to be visible and clickable
			waitForElementToBeVisible(addToCartHoverBtn);
			waitUntilElementIsClickable(addToCartHoverBtn);

			// Click the "Add to Cart" button
			addToCartHoverBtn.click();

			// Wait for the success popup to appear
			WebElement popup = waitForElementToBeVisible(productAddedToCartPopup);
			String message = productAddedToCartMessage.getText();

			// Verify the success message
			assertTrue(message.contains("Success: You have added"));
		} catch (Exception e) {
			System.out.println("Failed to add product to cart: " + e.getMessage());
		}

		return new CartPage(driver);
	}

	public void verifyThatProductIsAddedToCart() {
		WebElement popup = waitForElementToBeVisible(productAddedToCartPopup);

		String message = productAddedToCartMessage.getText();

		assertTrue(message.contains("Success: You have added"));

		waitUntilElementIsInvisible(productAddedToCartPopup);

	}

}