package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class OrderHistoryPage extends UiUtilities{
	
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
	
	
//	public void goToOrderHistoryPage() {
//		SideMenu sidebar = new SideMenu(driver);
//		sidebar.getButtonFromSideMenu("Order History").click();
//	}
	
	
	private void setOrderHistoryValues() {
		
		waitForVisibilityOfElementLocatedBy(cartTableHeaders);
		
		int orderIDColumn = getColumnHeaderIndex("Order ID", cartTableHeaders);
		int customerColumn = getColumnHeaderIndex("Customer", cartTableHeaders);
		int statusColumn = getColumnHeaderIndex("Status", cartTableHeaders);
		int totalColumn = getColumnHeaderIndex("Total", cartTableHeaders);
		int dateAddedColumn = getColumnHeaderIndex("Date Added", cartTableHeaders);
		//int dateModifiedColumn = getColumnHeaderIndex("Date Modified", cartTableHeaders);
		int numberOfItemsColums = getColumnHeaderIndex("No. of Products", cartTableHeaders);
		
		
		int tableRowsCount = getElements(By.xpath("//*[@class = 'table-responsive']/table/tbody/tr")).size();
		
		for(int i=1; i<=tableRowsCount;) {
			
			
			
			String orderId = getElement(i, orderIDColumn).getText();
			System.out.println("Order ID: " + orderId);
			
			customerName =  getElement(i, customerColumn).getText();
			System.out.println("Customer Name: " + customerName);
			
			status = getElement(i, statusColumn).getText();
			System.out.println("Status: " + status);
			
			totalAmount = getElement(i, totalColumn).getText().replace("$", "").trim();
			System.out.println("Total Amount: " + totalAmount);
			
			orderDate = getElement(i, dateAddedColumn).getText();
			System.out.println("Order Date: " + orderDate);
			
			numberOfItems = getElement(i, numberOfItemsColums).getText();
			System.out.println("Number of Items: " + numberOfItems);
			
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
		
		
		System.out.println("Expected Customer Name: " + expectedCustomerName);
		System.out.println("Expected Status: " + expectedStatus);
		System.out.println("Expected Total Amount: " + expectedTotalAmount);
		System.out.println("Expected Number of Items: " + expectedNumberOfItems);
		
		System.out.println("Actual Customer Name: " + customerName);
		System.out.println("Actual Status: " + status);
		System.out.println("Actual Total Amount: " + totalAmount);
		System.out.println("Actual Number of Items: " + numberOfItems);
		
		
		
		
	}
	
	private WebElement getElement(int rowIndex, int columnIndex) {
		WebElement tableCellEl =  driver.findElement(By.xpath("//*[@class = 'table-responsive']/table/tbody/tr["+rowIndex+"]/td["+columnIndex+"]"));
		return tableCellEl;
	}

}
