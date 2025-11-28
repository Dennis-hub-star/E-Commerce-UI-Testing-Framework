package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

/**
 * Represents the Order History Page of the application.
 * Provides methods to validate order history details.
 */
public class OrderHistoryPage extends UiUtilities {

	WebDriver driver;

	private String customerName;
	private String status;
	private String totalAmount;
	private String orderDate;
	private String numberOfItems;

	/**
	 * Constructor to initialize the OrderHistoryPage.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By cartTableHeaders = By.cssSelector(".table-bordered thead tr td");

	/**
	 * Sets the order history values by extracting data from the table.
	 */
	private void setOrderHistoryValues() {

		waitForVisibilityOfElementLocatedBy(cartTableHeaders);

		int customerColumn = getColumnHeaderIndex("Customer", cartTableHeaders);
		int statusColumn = getColumnHeaderIndex("Status", cartTableHeaders);
		int totalColumn = getColumnHeaderIndex("Total", cartTableHeaders);
		int dateAddedColumn = getColumnHeaderIndex("Date Added", cartTableHeaders);
		int numberOfItemsColums = getColumnHeaderIndex("No. of Products", cartTableHeaders);

		int tableRowsCount = getElements(By.xpath("//*[@class = 'table-responsive']/table/tbody/tr")).size();

		for (int i = 1; i <= tableRowsCount;) {

			customerName = getElement(i, customerColumn).getText();

			status = getElement(i, statusColumn).getText();

			totalAmount = getElement(i, totalColumn).getText().replace("$", "").trim();

			orderDate = getElement(i, dateAddedColumn).getText();

			numberOfItems = getElement(i, numberOfItemsColums).getText();

			break;

		}

	}

	/**
	 * Validates the order history details against expected values.
	 * 
	 * @param expectedCustomerName Expected customer name.
	 * @param expectedStatus Expected order status.
	 * @param expectedTotalAmount Expected total amount.
	 * @param expectedNumberOfItems Expected number of items in the order.
	 */
	public void validateOrderHistoryDetails(String expectedCustomerName, String expectedStatus,
			String expectedTotalAmount, String expectedNumberOfItems) {

		setOrderHistoryValues();
		verifyText(expectedCustomerName, customerName);
		verifyText(expectedStatus, status);
		verifyText(expectedTotalAmount, totalAmount);
		verifyText(getCurrentDate(), orderDate);
		verifyText(expectedNumberOfItems, numberOfItems);

	}

	/**
	 * Retrieves a specific cell element from the order history table.
	 * 
	 * @param rowIndex The row index of the cell.
	 * @param columnIndex The column index of the cell.
	 * @return The WebElement representing the cell.
	 */
	private WebElement getElement(int rowIndex, int columnIndex) {
		WebElement tableCellEl = driver.findElement(
				By.xpath("//*[@class = 'table-responsive']/table/tbody/tr[" + rowIndex + "]/td[" + columnIndex + "]"));
		return tableCellEl;
	}

}