package ui.tests.negativeTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.NegativeTestDataProviders;
import ui.pages.CartPage;
import ui.pages.LoginPage;
import ui.pages.SearchResultsPage;

public class SearchAndAddProductToCard extends BaseTest {

	@Test(dataProvider = "negativeSearchDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void searchInvalidProduct(HashMap<String, String> input) throws InterruptedException {
		
		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct(input.get("productName"));
		searchResults.verifyThatNoProductWasFound(input.get("resultsMessage"));
		
	}
	
	
	@Test(dataProvider = "addOutOfStockProductsToCartDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void addOutOfStockProductToCart(HashMap<String, String> input) throws InterruptedException {
		
		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct(input.get("productName"));
		searchResults.displayOnlyOutOfStockProducts(input.get("availability"));
		CartPage cartPage = searchResults.addProductToCart();
		searchResults.verifyThatProductIsAddedToCart();
		cartPage.goToCartPage();
		
		cartPage.verifyProductNotInStockAlert(input.get("alertMessage"));
		cartPage.removeItemFromCart();
		
		
		
	}
	
	
	@Test(dataProvider = "negativeProductQuantityUpdateDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void updateWithInvalidInputProductQuantityInCart(HashMap<String, String> input) throws InterruptedException {
		
		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct(input.get("productName"));
		searchResults.displayOnlyInStockProducts(input.get("availability"));
		CartPage cartPage = searchResults.addProductToCart();
		searchResults.verifyThatProductIsAddedToCart();
		cartPage.goToCartPage();

		cartPage.editProductQuantity(input.get("productQuantity"));
		cartPage.verifyProductNotInStockAlert(input.get("alertMessage"));
		cartPage.removeItemFromCart();
		
	}
}
