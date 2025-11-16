package ui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

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
	//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitForElementToBeVisible(WebElement element) {//// ***** delete??
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		// wait.until(ExpectedConditions.);
		return wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public void waitForElementLocatedBy(By locator) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}

	public Actions actions() {
		return new Actions(driver);
	}

	public WebElement waitForOneOfTheseElementsToBeVisible(By element1, By element2) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(element1));
		} catch (Exception e) {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(element2));
		}

	}

	public List<WebElement> getElements(By element) {

        // Wait for the elements located by the given locator to be visible
        waitForVisibilityOfElementLocatedBy(element);
        return driver.findElements(element);
	}

	public static void verifyText(String actual, String expected) {
		// Assert.assertTrue(actual.contains(expected));
		Assert.assertEquals(actual, expected);

	}

	public static void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	public WebElement waitUntilElementIsClickable(WebElement element) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
		
		
	}
	
	
	public void waitUntilElementIsInvisible(WebElement element) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public Select selectOptionFromDropdown(WebElement dropdownElement) {

		Select select = new Select(dropdownElement);
		return select;
	}
	
	public static List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// readFileToString --> Json file content past would get converted string
		// We past this because there a deprecation error on readFileToString method. So
		// it was basically crossed
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Converting the string to hashmap and we need to get a dependency called
		// ********************** jackson data bind FROM maven repository ******************

		ObjectMapper mapper = new ObjectMapper();

		// From ObjectMapper class there is a readValue method which we use to read a
		// string and then covert it to a hashmap
		// This readValue method expects two arguments, the first argument is the string
		// to be converted, the second argument is responsible for how the string should
		// be converted
		// So the second argument basically says create two hashmaps and then put them
		// inside a list
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	
	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException{
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);

	    String destPath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	    File file = new File(destPath);

	    // Create reports folder if it doesn't exist
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
	
	
	// This is the method to generate date in this format 31/10/2025
	public String getCurrentDate() {
		
		LocalDate today = LocalDate.now();  // gets current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);
        
        System.out.println(formattedDate); // e.g. 31/10/2025
		return formattedDate;
	}
	
	
	
	public static Object[][] getJsonDataFromFile(String filePath) throws IOException {
		String path = System.getProperty("user.dir")
				+ filePath;
		List<HashMap<String, String>> data = UiUtilities.getJsonDataToMap(path);
		Object[][] prepared = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			prepared[i][0] = data.get(i);
		}
		return prepared;
	}
	
	
	public static String getGlobalVariables(String property, String filePath) throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		//System.out.println(prop.getProperty(token));

		return prop.getProperty(property);

	}
	

}