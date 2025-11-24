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

public class UiUtilities {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public UiUtilities(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	}

	public WebElement waitForVisibilityOfElementLocatedBy(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitForElementToBeVisible(WebElement element) {//// ***** delete??

		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForStalenessAnsOfElement(WebElement element) {
		wait.until(ExpectedConditions.stalenessOf(element));

	}

	public WebElement waitForStalenessThenVisibilityOfElementLocated(By locator) {
		waitForStalenessAnsOfElement(driver.findElement(locator));
		return waitForVisibilityOfElementLocatedBy(locator);
	}

	public Actions actions() {
		return new Actions(driver);
	}

	public WebElement waitForOneOfTheseElementsToBeVisible(By element1, By element2) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(element1));
		} catch (Exception e) {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(element2));
		}

	}

	public List<WebElement> getElements(By element) {

		waitForVisibilityOfElementLocatedBy(element);
		return driver.findElements(element);
	}

	public static void verifyText(String actual, String expected) {
		Assert.assertEquals(actual, expected);

	}

	public static void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	public WebElement waitUntilElementIsClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitForInvisibilityOfElementLocatedBy(By element) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
	
	public void waitForPresenceOfElementLocatedBy(By element) {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	public Select selectOptionFromDropdown(WebElement dropdownElement) {

		Select select = new Select(dropdownElement);
		return select;
	}

	public static List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destPath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File file = new File(destPath);

		file.getParentFile().mkdirs();

		FileUtils.copyFile(source, file);

		return destPath;
	}

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

	public String getCurrentDate() {

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = today.format(formatter);

		return formattedDate;
	}

}