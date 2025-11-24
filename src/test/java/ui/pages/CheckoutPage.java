package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ui.utils.UiUtilities;

public class CheckoutPage extends UiUtilities {

	WebDriver driver;

	CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@for='input-payment-address-existing']")
	private WebElement existingAddressRadioBtn;

//	@FindBy(xpath = "//*[@id = 'input-payment-address-new']/following-sibling::label")
//	private WebElement newAddressRadioBtn;

	By newAddressRadioBtnBy = By.xpath("//*[@id = 'input-payment-address-new']/following-sibling::label");

	@FindBy(id = "input-shipping-address-same")
	private WebElement shippingAddressSameAsBillingCheckbox;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-payment-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-payment-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-payment-company")
	private WebElement companyField;

	@FindBy(id = "input-payment-address-1")
	private WebElement address1Field;

	@FindBy(id = "input-payment-address-2")
	private WebElement address2Field;

	@FindBy(id = "input-payment-city")
	private WebElement cityField;

	@FindBy(id = "input-payment-postcode")
	private WebElement postcodeField;

	@FindBy(id = "input-payment-country")
	private WebElement countryDropdown;

	@FindBy(id = "input-payment-zone")
	private WebElement regionStateDropdown;

	@FindBy(className = "invalid-feedback")
	private WebElement billingDetailsErrorMessage;

	@FindBy(id = "input-comment")
	private WebElement commentField;

	@FindBy(xpath = "//*[@for = 'input-agree']")
	private WebElement termsAndConditionsCheckbox;

	@FindBy(id = "button-save")
	private WebElement continueBtn;

	By tableHeaders = By.xpath("//*[@id = 'checkout-cart']/table/thead/tr/th");
	By tableRows = By.xpath("//*[@id = 'checkout-cart']/table/tbody/tr");
	By dropdownOption = By.xpath("//option[text() = 'Gauteng']");
	By cartEmpty = By.xpath("//*[text() = 'Shopping Cart']/following-sibling::p");

	public void fillInBillingDetails(String firstName, String lastName, String company, String address1,
			String address2, String city, String postcode, String country, String regionState) {

		waitForVisibilityOfElementLocatedBy(newAddressRadioBtnBy).click();

		firstNameField.clear();
		firstNameField.sendKeys(firstName);

		lastNameField.clear();
		lastNameField.sendKeys(lastName);

		companyField.clear();
		companyField.sendKeys(company);

		address1Field.clear();
		address1Field.sendKeys(address1);

		address2Field.clear();
		address2Field.sendKeys(address2);

		cityField.clear();
		cityField.sendKeys(city);

		postcodeField.clear();
		postcodeField.sendKeys(postcode);

		selectFromDropdown(countryDropdown, country);

		if (!country.equalsIgnoreCase(" --- Please Select --- ")) {
			waitForPresenceOfElementLocatedBy(dropdownOption);
			selectFromDropdown(regionStateDropdown, regionState);
		}

	}

	public void verifyErrorMessageForMandatoryBillingDetails(String expectedMessage) {

		verifyText(expectedMessage, billingDetailsErrorMessage.getText().trim());

	}

	private void selectFromDropdown(WebElement dropdown, String option) {

		waitUntilElementIsClickable(dropdown);
		selectOptionFromDropdown(dropdown).selectByVisibleText(option);

	}

	public void acceptTermsAndConditions(String accept) {
		waitUntilElementIsClickable(termsAndConditionsCheckbox);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", termsAndConditionsCheckbox);

		if (accept.equalsIgnoreCase("yes")) {
			termsAndConditionsCheckbox.click();
		}

	}

	public void proceedToConfirmationPage() throws InterruptedException {
		waitUntilElementIsClickable(continueBtn);
		continueBtn.click();

		Thread.sleep(7000);

	}

	public OrderConfirmPage validateCheckoutItemsTable(String expectedProductName, String expectedQuantity,
			String expectedUnitPrice, String expectedSubTotal) {

		int productNameColumnIndex = getColumnHeaderIndex("Product Name", tableHeaders);
		int quantityColumnIndex = getColumnHeaderIndex("Quantity", tableHeaders);
		int unitPriceColumnIndex = getColumnHeaderIndex("Unit Price", tableHeaders);
		int totalColumnIndex = getColumnHeaderIndex("Total", tableHeaders);

		List<WebElement> checkoutTableRows = getElements(tableRows);

		for (int r = 1; r <= checkoutTableRows.size(); r++) {

			WebElement productNameInRow = driver
					.findElement(By.xpath(getBaseElementLocator(r, productNameColumnIndex) + "/a"));
			WebElement quantityInRow = driver
					.findElement(By.xpath(getBaseElementLocator(r, quantityColumnIndex) + "/div/input"));
			WebElement unitPriceInRow = driver.findElement(By.xpath(getBaseElementLocator(r, unitPriceColumnIndex)));
			WebElement totalInRow = driver.findElement(By.xpath(getBaseElementLocator(r, totalColumnIndex)));

			assertTrue(productNameInRow.getText().contains(expectedProductName));
			verifyText(expectedQuantity, quantityInRow.getAttribute("value"));
			verifyText(expectedUnitPrice, unitPriceInRow.getText().replace("$", "").trim());
			verifyText(expectedSubTotal, totalInRow.getText().replace("$", "").trim());

		}

		return new OrderConfirmPage(driver);

	}

	private String getSummaryTableAmountOf(String item) {

		WebElement itemInSummary = driver
				.findElement(By.xpath("//*[@id='checkout-total']//*[text() = '" + item + "']/following-sibling::td"));

		return itemInSummary.getText().replace("$", "").trim();

	}

	public void validateCartSummaryTable(String expectedSubTotal, String expectedFlatShippingRate,
			String expectedTotal) {

		String subTotal = getSummaryTableAmountOf("Sub-Total:");
		String flatShippingRate = getSummaryTableAmountOf("Flat Shipping Rate:");
		String total = getSummaryTableAmountOf("Total:");

		verifyText(expectedSubTotal, subTotal);
		verifyText(expectedFlatShippingRate, flatShippingRate);
		verifyText(expectedTotal, total);

	}

	private String getBaseElementLocator(int rowIndex, int columnIndex) {
		return "//*[@id = 'checkout-cart']/table/tbody/tr[" + rowIndex + "]/td[" + columnIndex + "]";
	}

	public void removeItemFromCheckout() {
		// To be implemented

		// *[@id = 'checkout-cart']/table/tbody/tr[1]/td[3]/div/div/button[2]
		List<WebElement> checkoutTableRows = getElements(tableRows);
		for (int r = 1; r <= checkoutTableRows.size(); r++) {
			WebElement removeBtn = driver.findElement(By.xpath(getBaseElementLocator(r, 3) + "/div/div/button[2]"));
			removeBtn.click();
			// waitForInvisibilityOfElementLocatedBy(By.xpath(getBaseElementLocator(r, 1)));
		}

		String emptyCartMessage = waitForVisibilityOfElementLocatedBy(cartEmpty).getText();

		//assertTrue(emptyCartMessage.contains("Your shopping cart is empty!"));
		verifyText(emptyCartMessage, "Your shopping cart is empty!");

	}

}
