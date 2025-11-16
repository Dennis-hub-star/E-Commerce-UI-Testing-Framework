package ui.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import ui.components.NavigationBar;
import ui.pages.HomePage;
import ui.utils.UiUtilities;

public class BaseTest {

	public WebDriver driver;
	protected HomePage homePage;
	protected NavigationBar navigationBar;

	public WebDriver initializeDriver() throws IOException {

		//Properties props = new Properties();

		// Converting the file to input stream
		// The the file path of the properties file should be passed in as an argument
		// We use this System.getProperty("user.dir") to shorted the file path
		//FileInputStream fileConversion = new FileInputStream(
				//System.getProperty("user.dir") + "/src/test/resources/ui/properties/globalData.properties");
		// This requires an input stream as an argument
		//props.load(fileConversion);
		
		String filePath = System.getProperty("user.dir") + "/src/test/resources/ui/properties/globalData.properties";
		String prop = UiUtilities.getGlobalVariables("browser", filePath);
		// Reading/getting properties coming from maven
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop;
		// String browser = "firefox";

		if (browser.contains("chrome")) {
			// ************** For Headless Mode *******************

			ChromeOptions options = new ChromeOptions();
			// ****************************************************
			WebDriverManager.chromedriver().setup();

			if (browser.contains("headless")) {
				options.addArguments("headless"); // ************** For Headless Mode *******************
			}
			driver = new ChromeDriver(options);
			
			// The following line of code ensures that the applications runs on a full screen mode, but optional
//			driver.manage().window().setSize(new Dimension(1920, 1080)); 

		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		return driver;
	}

	@BeforeMethod
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
		driver.manage().window().maximize();
//		homePage = new HomePage(driver);
		navigationBar = new NavigationBar(driver);
	}

	@AfterMethod
	public void tearDown() {
		// driver.quit();
		driver.close();
	}

//	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//
//		// readFileToString --> Json file content past would get converted string
//		// We past this because there a deprecation error on readFileToString method. So
//		// it was basically crossed
//		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
//
//		// Converting the string to hashmap and we need to get a dependency called
//		// ********************** jackson data bind FROM maven repository ******************
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		// From ObjectMapper class there is a readValue method which we use to read a
//		// string and then covert it to a hashmap
//		// This readValue method expects two arguments, the first argument is the string
//		// to be converted, the second argument is responsible for how the string should
//		// be converted
//		// So the second argument basically says create two hashmaps and then put them
//		// inside a list
//		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
//				new TypeReference<List<HashMap<String, String>>>() {
//				});
//
//		return data;
//	}

}
