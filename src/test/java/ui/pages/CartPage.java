package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class CartPage extends UiUtilities {

	private WebDriver driver;
	private String unitPrice;
	private String total;
	private String quantity;
	private String subTotal;
	private String grandTotal;

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

	public void goToCartPage() {

		waitUntilElementIsClickable(cartIcon);
		cartIcon.click();
		waitUntilElementIsClickable(editCartLink);
		editCartLink.click();

	}

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

	public void verifyThatProductQuantityGotUpdated() {
		WebElement alert = waitForVisibilityOfElementLocatedBy(successAlert);
		try {

			assertTrue(alert.getText().contains("Success: You have modified your shopping cart!"));
		} catch (Exception e) {
			System.out.println("Exception in verifying success alert: " + e.getMessage());
			System.out.println("The success message is :" + alert.getText());
		}
	}

	public void goToCheckoutPage() {
		waitUntilElementIsClickable(checkoutBtn);
		checkoutBtn.click();
	}

	public void verifyProductNotInStockAlert(String string) {
		// TODO Auto-generated method stub

		String alertMessage = productNotInStockAlert.getText().split("\n")[0].trim();
		verifyText(alertMessage, string);

	}

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
