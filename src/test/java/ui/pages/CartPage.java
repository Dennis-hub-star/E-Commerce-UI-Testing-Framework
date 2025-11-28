package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Cart Page of the application.
 * Provides methods to interact with the cart, such as editing product quantities,
 * verifying amounts, and proceeding to checkout.
 */
public class CartPage extends UiUtilities {

	private WebDriver driver;
	private String unitPrice;
	private String total;
	private String quantity;
	private String subTotal;
	private String grandTotal;

	/**
	 * Constructor to initialize the CartPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@class = 'cart-icon']")
	private WebElement cartIcon;

	@FindBy(xpath = "//*[@id= 'entry_217850']/a")
	private WebElement editCartLink;

	@FindBy(xpath = "//*[@class  ='table table-bordered']/thead/following-sibling::tbody/tr[1]/td[2]/a")
	private WebElement productInCart;

	@FindBy(css = ".alert-danger")
	private WebElement productNotInStockAlert;

	By cartTableHeaders = By.cssSelector(".table-bordered thead tr th");

	By tableRows = By.xpath("//*[@class  ='table table-bordered']/thead/following-sibling::tbody/tr");
	By cartEmpty = By.xpath("//*[text() = 'Shopping Cart']/following-sibling::p");

	By successAlert = By.className("alert-success");

	@FindBy(css = ".buttons a:last-of-type")
	private WebElement checkoutBtn;

	/**
	 * Navigates to the cart page.
	 */
	public void goToCartPage() {

		waitUntilElementIsClickable(cartIcon);
		cartIcon.click();
		waitUntilElementIsClickable(editCartLink);
		editCartLink.click();

	}

	/**
	 * Edits the quantity of a product in the cart.
	 * 
	 * @param quantity The new quantity to set.
	 * @return CheckoutPage object representing the checkout page.
	 */
	public CheckoutPage editProductQuantity(String quantity) {

		List<WebElement> cartTableRows = getElements(tableRows);

		int quantityColumnIndex = getColumnHeaderIndex("Quantity", cartTableHeaders);
		int unitPriceColumnIndex = getColumnHeaderIndex("Unit Price", cartTableHeaders);
		int totalColumnIndex = getColumnHeaderIndex("Total", cartTableHeaders);

		for (int r = 1; r <= cartTableRows.size(); r++) {

			WebElement quantityField = driver
					.findElement(By.xpath(getBaseElementLocator(r, quantityColumnIndex) + "/div/input"));
			WebElement updateQuantityBtn = driver
					.findElement(By.xpath(getBaseElementLocator(r, quantityColumnIndex) + "/div/div/button[1]"));

			quantityField.clear();
			quantityField.sendKeys(quantity);
			waitUntilElementIsClickable(updateQuantityBtn).click();

			WebElement unitPriceInRow = driver.findElement(By.xpath(getBaseElementLocator(r, unitPriceColumnIndex)));

			WebElement totalInRow = driver.findElement(By.xpath(getBaseElementLocator(r, totalColumnIndex)));

			unitPrice = unitPriceInRow.getText().replace("$", "").trim();
			total = totalInRow.getText().replace("$", "").trim();
			this.quantity = quantity;

		}
		return new CheckoutPage(driver);

	}

	/**
	 * Verifies the amounts in the cart against expected values.
	 * 
	 * @param expectedUnitPrice Expected unit price of the product.
	 * @param flatShippingRate Expected flat shipping rate.
	 */
	public void verifyAmountsInCart(String expectedUnitPrice, String flatShippingRate) {

		double expectedTot = Double.parseDouble(unitPrice) * Integer.parseInt(quantity); // Calculate
		String expectedTotal = String.valueOf(String.format("%.2f", expectedTot));

		verifyText(expectedUnitPrice, unitPrice);
		verifyText(expectedTotal, total);

	};

	private void setCartSummeryAmounts() {

		WebElement subTotalElement = driver.findElement(By.xpath(
				"//*[@class='table table-bordered m-0']/tbody/tr//*[text() = 'Sub-Total:']/following-sibling::td"));
		subTotal = subTotalElement.getText().replace("$", "").trim();

		WebElement grandTotalElement = driver.findElement(By
				.xpath("//*[@class='table table-bordered m-0']/tbody/tr//*[text() = 'Total:']/following-sibling::td"));
		grandTotal = grandTotalElement.getText().replace("$", "").trim();
	}

	/**
	 * Validates the cart summary table against the expected subtotal.
	 * 
	 * @param subTotal Expected subtotal.
	 */
	public void validateCartSummaryTable(String subTotal) {

		setCartSummeryAmounts();
		verifyText(subTotal, this.subTotal);
		verifyText(subTotal, this.grandTotal);

	}

	private String getBaseElementLocator(int rowIndex, int unitPriceColumnIndex) {

		String baseElementLocator = "//*[@class  ='table table-bordered']/thead/following-sibling::tbody/tr[" + rowIndex
				+ "]/td[" + unitPriceColumnIndex + "]";

		return baseElementLocator;

	}

	/**
	 * Verifies that the product quantity in the cart was updated successfully.
	 */
	public void verifyThatProductQuantityGotUpdated() {
		WebElement alert = waitForVisibilityOfElementLocatedBy(successAlert);
		try {

			assertTrue(alert.getText().contains("Success: You have modified your shopping cart!"));
		} catch (Exception e) {
			System.out.println("Exception in verifying success alert: " + e.getMessage());
			System.out.println("The success message is :" + alert.getText());
		}
	}

	/**
	 * Navigates to the checkout page.
	 */
	public void goToCheckoutPage() {
		waitUntilElementIsClickable(checkoutBtn);
		checkoutBtn.click();
	}

	/**
	 * Verifies that a product not in stock displays the correct alert message.
	 * 
	 * @param string The expected alert message.
	 */
	public void verifyProductNotInStockAlert(String string) {
		// TODO Auto-generated method stub

		String alertMessage = productNotInStockAlert.getText().split("\n")[0].trim();
		verifyText(alertMessage, string);

	}

	/**
	 * Removes an item from the cart and verifies the cart is empty.
	 */
	public void removeItemFromCart() {
		List<WebElement> cartTableRows = getElements(tableRows);

		for (int r = 1; r <= cartTableRows.size(); r++) {
			int quantityColumnIndex = getColumnHeaderIndex("Quantity", cartTableHeaders);

			WebElement removeBtn = driver
					.findElement(By.xpath(getBaseElementLocator(r, quantityColumnIndex) + "/div/div/button[2]"));
			waitUntilElementIsClickable(removeBtn);
			removeBtn.click();

			String emptyCartMessage = waitForVisibilityOfElementLocatedBy(cartEmpty).getText();

			verifyText(emptyCartMessage, "Your shopping cart is empty!");
		}

	}

}