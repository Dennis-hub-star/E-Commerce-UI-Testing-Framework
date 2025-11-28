package ui.tests.positiveTests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.DataProviders;
import ui.pages.AccountPage;
import ui.pages.CartPage;
import ui.pages.CheckoutPage;
import ui.pages.LoginPage;
import ui.pages.OrderConfirmPage;
import ui.pages.OrderHistoryPage;
import ui.pages.SearchResultsPage;

/**
 * This class contains positive test cases for placing an order and validating order history.
 * It ensures that the end-to-end purchase flow and order history functionality work as expected.
 */
public class OrderPlacementAndOrderHistory extends BaseTest {

	/**
	 * Test method to verify the end-to-end order placement process.
	 * 
	 * @param input A HashMap containing test data such as email, product details, and pricing information.
	 * @throws InterruptedException If the thread is interrupted during execution.
	 */
	@Test(dataProvider = "endToEndPurchaseData", dataProviderClass = DataProviders.class)
	public void placeOrderAndValidateOrderHistoryTest(HashMap<String, String> input) throws InterruptedException {
		// To be implemented
		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct(input.get("productName"));
		searchResults.displayOnlyInStockProducts(input.get("availability"));
		searchResults.verifyThatProductIsInResults();
		CartPage cartPage = searchResults.addProductToCart();
		searchResults.verifyThatProductIsAddedToCart();

		cartPage.goToCartPage();
		CheckoutPage checkout = cartPage.editProductQuantity(input.get("productQuantity"));
		cartPage.verifyAmountsInCart(input.get("unitPrice"), input.get("flatShippingRate"));
		cartPage.validateCartSummaryTable(input.get("subTotal"));
		cartPage.verifyThatProductQuantityGotUpdated();

		cartPage.goToCheckoutPage();

		OrderConfirmPage confirmOrderDetails = checkout.validateCheckoutItemsTable(input.get("productName"),
				input.get("productQuantity"), input.get("unitPrice"), input.get("subTotal"));

		checkout.validateCartSummaryTable(input.get("subTotal"), input.get("flatShippingRate"), input.get("total"));
		checkout.acceptTermsAndConditions(input.get("agreeToTerms"));
		checkout.proceedToConfirmationPage();

		confirmOrderDetails.verifyOrderConfirmationDetailsTable(input.get("productName"), input.get("productQuantity"),
				input.get("unitPrice"), input.get("subTotal"));

		confirmOrderDetails.validateOrderConfirmationSummaryTable(input.get("subTotal"), input.get("flatShippingRate"),
				input.get("total"));

		confirmOrderDetails.confirmThatOrderIsPlaced();

	}

	/**
	 * Test method to verify the order history details.
	 * 
	 * @param input A HashMap containing test data such as customer name, order status, and total amount.
	 * @throws InterruptedException If the thread is interrupted during execution.
	 * @throws IOException If an I/O error occurs during execution.
	 */
	@Test(dataProvider = "orderHistoryData", dataProviderClass = DataProviders.class, dependsOnMethods = {
			"placeOrderAndValidateOrderHistoryTest" })
	public void orderHistoryTest(HashMap<String, String> input) throws InterruptedException, IOException {
		LoginPage userLogin = navigationBar.goToLoginPage();
		AccountPage accountPage = userLogin.login(input.get("email"), input.get("password"));
		OrderHistoryPage orderHistory = accountPage.goToOrderHistory();
		orderHistory.validateOrderHistoryDetails(input.get("customerName"), input.get("status"),
				input.get("totalAmount"), input.get("numberOfItems"));

	}

}