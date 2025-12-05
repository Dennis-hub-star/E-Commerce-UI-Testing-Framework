package ui.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * UiUtilities class provides utility methods for common UI interactions.
 * It includes methods for waiting, interacting with elements, and handling data.
 */
public class UiUtilities {

	protected WebDriver driver;
	protected WebDriverWait wait;

	/**
	 * Constructor to initialize UiUtilities with a WebDriver instance.
	 * 
	 * @param driver WebDriver instance to interact with the browser.
	 */
	public UiUtilities(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	}

	/**
	 * Waits for an element to be visible using its locator.
	 * 
	 * @param locator Locator of the element to wait for.
	 * @return The visible WebElement.
	 */
	public WebElement waitForVisibilityOfElementLocatedBy(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * Waits for an element to be visible.
	 * 
	 * @param element The WebElement to wait for.
	 * @return The visible WebElement.
	 */
	public WebElement waitForElementToBeVisible(WebElement element) {//// ***** delete??

		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * Waits for an element to become stale.
	 * 
	 * @param element The WebElement to wait for staleness.
	 */
	public void waitForStalenessAnsOfElement(WebElement element) {
		wait.until(ExpectedConditions.stalenessOf(element));

	}

	/**
	 * Waits for an element to become stale and then visible using its locator.
	 * 
	 * @param locator Locator of the element to wait for.
	 * @return The visible WebElement after staleness.
	 */
	public WebElement waitForStalenessThenVisibilityOfElementLocated(By locator) {
		waitForStalenessAnsOfElement(driver.findElement(locator));
		return waitForVisibilityOfElementLocatedBy(locator);
	}

	/**
	 * Provides an Actions instance for performing advanced user interactions.
	 * 
	 * @return Actions instance.
	 */
	public Actions actions() {
		return new Actions(driver);
	}

	/**
	 * Waits for one of two elements to be visible.
	 * 
	 * @param element1 Locator of the first element.
	 * @param element2 Locator of the second element.
	 * @return The visible WebElement.
	 */
	public WebElement waitForOneOfTheseElementsToBeVisible(By element1, By element2) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(element1));
		} catch (Exception e) {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(element2));
		}

	}

	/**
	 * Retrieves a list of elements using their locator.
	 * 
	 * @param element Locator of the elements to retrieve.
	 * @return List of WebElements.
	 */
	public List<WebElement> getElements(By element) {

		waitForVisibilityOfElementLocatedBy(element);
		return driver.findElements(element);
	}

	/**
	 * Verifies that the actual text matches the expected text.
	 * 
	 * @param actual The actual text.
	 * @param expected The expected text.
	 */
	public static void verifyText(String actual, String expected) {
		Assert.assertEquals(actual, expected);

	}

	/**
	 * Asserts that a condition is true.
	 * 
	 * @param condition The condition to assert.
	 */
	public static void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	/**
	 * Waits until an element is clickable.
	 * 
	 * @param element The WebElement to wait for.
	 * @return The clickable WebElement.
	 */
	public WebElement waitUntilElementIsClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * Waits for an element to become invisible using its locator.
	 * 
	 * @param element Locator of the element to wait for.
	 */
	public void waitForInvisibilityOfElementLocatedBy(By element) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
	
	/**
	 * Waits for an element to be present in the DOM using its locator.
	 * 
	 * @param element Locator of the element to wait for.
	 */
	public void waitForPresenceOfElementLocatedBy(By element) {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	/**
	 * Selects an option from a dropdown element.
	 * 
	 * @param dropdownElement The dropdown WebElement.
	 * @return Select instance for interacting with the dropdown.
	 */
	public Select selectOptionFromDropdown(WebElement dropdownElement) {

		Select select = new Select(dropdownElement);
		return select;
	}

	/**
	 * Reads JSON data from a file and converts it to a list of maps.
	 * 
	 * @param filePath Path to the JSON file.
	 * @return List of HashMaps containing the JSON data.
	 * @throws IOException If there is an error reading the file.
	 */
	public static List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	/**
	 * Captures a screenshot and saves it to the specified path.
	 * 
	 * @param testCaseName Name of the test case for the screenshot.
	 * @param driver WebDriver instance.
	 * @return Path to the saved screenshot.
	 * @throws IOException If there is an error saving the screenshot.
	 */
	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destPath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		File file = new File(destPath);

		file.getParentFile().mkdirs();

		FileUtils.copyFile(source, file);

		return destPath;
	}

	/**
	 * Retrieves the index of a column header in a table.
	 * 
	 * @param columnName Name of the column header.
	 * @param tableHeaders Locator for the table headers.
	 * @return Index of the column header.
	 */
	public int getColumnHeaderIndex(String columnName, By tableHeaders) {

		List<WebElement> headers = getElements(tableHeaders);
		int headerColumnIndex = 0;

		for (int i = 1; i <= headers.size(); i++) {

			if (headers.get(i - 1).getText().equalsIgnoreCase(columnName)) {

				headerColumnIndex = i;
			}

		}
		return headerColumnIndex;

	}

	/**
	 * Retrieves the current date in the format dd/MM/yyyy.
	 * 
	 * @return The formatted current date as a string.
	 */
	public String getCurrentDate() {

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = today.format(formatter);

		return formattedDate;
	}

}