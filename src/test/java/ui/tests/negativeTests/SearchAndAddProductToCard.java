package ui.tests.negativeTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.NegativeTestDataProviders;
import ui.pages.CartPage;
import ui.pages.LoginPage;
import ui.pages.SearchResultsPage;

/**
 * This class contains negative test cases for searching and adding products to the cart.
 * It verifies that invalid product searches and attempts to add out-of-stock products are handled correctly.
 */
public class SearchAndAddProductToCard extends BaseTest {

	/**
	 * Test method to verify that searching for an invalid product returns no results.
	 * 
	 * @param input A HashMap containing test data such as email, password, and product name.
	 * @throws InterruptedException If the thread is interrupted during execution.
	 */
	@Test(dataProvider = "negativeSearchDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void searchInvalidProduct(HashMap<String, String> input) throws InterruptedException {
		
		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct(input.get("productName"));
		searchResults.verifyThatNoProductWasFound(input.get("resultsMessage"));
		
	}
	
	
	/**
	 * Test method to verify that adding out-of-stock products to the cart is handled correctly.
	 * 
	 * @param input A HashMap containing test data such as email, password, and product details.
	 * @throws InterruptedException If the thread is interrupted during execution.
	 */
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
	
	
	/**
	 * Test method to verify that updating product quantity in the cart with invalid input is handled correctly.
	 * 
	 * @param input A HashMap containing test data such as email, password, and product quantity.
	 * @throws InterruptedException If the thread is interrupted during execution.
	 */
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