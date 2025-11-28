package ui.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ui.utils.UiUtilities;

public class SideMenu extends UiUtilities {

	WebDriver driver;

	public SideMenu(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	By sideMenuItems = By.className("list-group-item");

	public WebElement getButtonFromSideMenu(String buttonName) {
		WebElement actualItem = null;
		List<WebElement> menuItems = getElements(sideMenuItems);

		for (WebElement item : menuItems) {

			if (item.getText().contains(buttonName)) {
				actualItem = item;

				break;
			}
		}

		return actualItem;

	}

}