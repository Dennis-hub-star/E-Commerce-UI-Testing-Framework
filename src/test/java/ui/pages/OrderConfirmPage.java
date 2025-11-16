package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class OrderConfirmPage extends UiUtilities {

	WebDriver driver;

	OrderConfirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By orderDetailsTableHeaders = By.cssSelector(".table-responsive table thead tr td");

	By orderDetailsTableRows = By.xpath("//*[@class = 'table-responsive mb-4']/table/tbody/tr");

	@FindBy(xpath = " //*[@class = 'table-responsive mb-4']/table/tbody/tr[1]/td[1]")
	private WebElement tableBodyItem;

	@FindBy(xpath = " //*[@class = 'table-responsive mb-4']/table/tfoot/tr/td[1]")
	private WebElement tableFooterItem;

	@FindBy(xpath = " //*[@class = 'table-responsive mb-4']/table/tfoot/tr/td[2]")
	private WebElement tableFooterValue;

	@FindBy(id = "button-confirm")
	private WebElement confirmOrderBtn;

	public void verifyOrderConfirmationDetailsTable(String expectedProductName, String expectedQuantity,
			String expectedUnitPrice, String expectedSubTotal)

	{
		waitForVisibilityOfElementLocatedBy(orderDetailsTableHeaders);
		List<WebElement> tableRows = getElements(orderDetailsTableRows);

		int productNameColumn = getColumnHeaderIndex("Product Name", orderDetailsTableHeaders);
		int quatityColumn = getColumnHeaderIndex("Quantity", orderDetailsTableHeaders);
		int priceColumn = getColumnHeaderIndex("Price", orderDetailsTableHeaders);
		int totalColumn = getColumnHeaderIndex("Total", orderDetailsTableHeaders);

		for (int i = 1; i <= tableRows.size(); i++) {

			WebElement product = getElement(i, productNameColumn);
			WebElement quantity = getElement(i, quatityColumn);
			WebElement price = getElement(i, priceColumn);
			WebElement total = getElement(i, totalColumn);

			assertTrue(product.getText().contains(expectedProductName));
			verifyText(expectedQuantity, quantity.getText());
			verifyText(expectedUnitPrice, price.getText().replace("$", "").trim());
			verifyText(expectedSubTotal, total.getText().replace("$", "").trim());

		}

	}

	public void validateOrderConfirmationSummaryTable(String expectedSubTotal, String expectedFlatShippingRate,
			String expectedTotal) {

		String subTotal = getSummaryTableAmountOf("Sub-Total:");
		String flatShippingRate = getSummaryTableAmountOf("Flat Shipping Rate:");
		String total = getSummaryTableAmountOf("Total:");

		verifyText(expectedSubTotal, subTotal);
		verifyText(expectedFlatShippingRate, flatShippingRate);
		verifyText(expectedTotal, total);
	}

	private String getSummaryTableAmountOf(String item) {

		WebElement itemInSummary = driver
				.findElement(By.xpath("//tfoot//*[text() = '" + item + "']/parent::td/following-sibling::td"));
		return itemInSummary.getText().replace("$", "").trim();
	}

	public WebElement getElement(int rowIndex, int columnIndex) {
		WebElement cell = driver.findElement(By.xpath(
				"//*[@class = 'table-responsive mb-4']/table/tbody/tr[" + rowIndex + "]/td[" + columnIndex + "]"));

		return cell;

	}

//	public void verifyOrderDetailsTableFooter(String item, String value) {
//		
//		waitForElementToBeVisible(tableFooterItem);
//		verifyText(tableFooterItem.getText(), item);
//		verifyText(tableFooterValue.getText(), value);
//		
//	}

	public void confirmThatOrderIsPlaced() throws InterruptedException {

		waitUntilElementIsClickable(confirmOrderBtn).click();

		Thread.sleep(5000);
		RegistrationSuccessPage successPage = new RegistrationSuccessPage(driver);

		successPage.verifyActionSuccess("Your order has been placed!");

	}

}
