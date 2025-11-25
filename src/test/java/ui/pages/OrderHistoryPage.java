package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class OrderHistoryPage extends UiUtilities {

	WebDriver driver;

	private String customerName;
	private String status;
	private String totalAmount;
	private String orderDate;
	private String numberOfItems;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By cartTableHeaders = By.cssSelector(".table-bordered thead tr td");

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

	public void validateOrderHistoryDetails(String expectedCustomerName, String expectedStatus,
			String expectedTotalAmount, String expectedNumberOfItems) {

		setOrderHistoryValues();
		verifyText(expectedCustomerName, customerName);
		verifyText(expectedStatus, status);
		verifyText(expectedTotalAmount, totalAmount);
		verifyText(getCurrentDate(), orderDate);
		verifyText(expectedNumberOfItems, numberOfItems);

	}

	private WebElement getElement(int rowIndex, int columnIndex) {
		WebElement tableCellEl = driver.findElement(
				By.xpath("//*[@class = 'table-responsive']/table/tbody/tr[" + rowIndex + "]/td[" + columnIndex + "]"));
		return tableCellEl;
	}

}
