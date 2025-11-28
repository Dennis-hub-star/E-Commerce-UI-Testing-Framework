package ui.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import common.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import ui.components.NavigationBar;
import ui.pages.HomePage;

/**
 * BaseTest class provides the setup and teardown methods for UI tests.
 * It initializes the WebDriver and launches the application before each test.
 */
public class BaseTest {

	public WebDriver driver;
	protected HomePage homePage;
	protected NavigationBar navigationBar;
	private String file = "ui.properties.globalData";

	/**
	 * Initializes the WebDriver based on the browser specified in the configuration.
	 * 
	 * @return WebDriver instance for the specified browser.
	 * @throws IOException If there is an error reading the configuration file.
	 */
	public WebDriver initializeDriver() throws IOException {


		
		String file = "ui.properties.globalData";
		String prop = Utilities.getGlobalValue("browser", file);
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop;
		if (browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browser.contains("headless")) {
				options.addArguments("headless"); 
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

	/**
	 * Launches the application and initializes the navigation bar.
	 * 
	 * @throws IOException If there is an error reading the configuration file.
	 */
	@BeforeMethod
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get(Utilities.getGlobalValue("siteURL", file));
		driver.manage().window().maximize();
		navigationBar = new NavigationBar(driver);
		
	}

	/**
	 * Closes the browser after each test.
	 */
	@AfterMethod
	public void tearDown() {
		driver.close();
	}



}