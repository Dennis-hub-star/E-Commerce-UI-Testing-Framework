package ui.tests;

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
import ui.pages.RegistrationPage;
import ui.pages.RegistrationSuccessPage;
import ui.pages.SearchResultsPage;

public class RegisterAndLogin extends BaseTest {

	// static String email;

//	@Test(dataProvider = "validRegistrationData", dataProviderClass = DataProviders.class)
	public void test(HashMap<String, String> input) {

		boolean agreePrivacyPolicy = Boolean.parseBoolean(input.get("agreePrivacyPolicy"));
		String email = input.get("email") + System.currentTimeMillis() + "@gmail.com";

		RegistrationPage registrationPage = navigationBar.goToRegistrationPage();
		RegistrationSuccessPage registrationSuccess = registrationPage.register(input.get("firstName"),
				input.get("lastName"), email, input.get("telephone"), input.get("password"),
				input.get("confirmPassword"), input.get("subscribeNewsletter"), agreePrivacyPolicy);

		registrationSuccess.verifyThatUserRegistered();

	}

//	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void testLogin(HashMap<String, String> input) {

		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		userLogin.verifyThatUserLoggedIn();
//		userLogin.userLogout();
//		userLogin.verifyThatUserLoggedOut();

	}

//	@Test(dependsOnMethods =  {"testLogin"} )
	public void testSearchAndAddProductToCart(HashMap<String, String> input) throws InterruptedException {

//		LoginPage userLogin = navigationBar.goToLoginPage();
//		SearchResultsPage searchResults = userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct("Iphone");
		searchResults.verifyThatProductIsInResults();
		searchResults.addProductToCart();
		searchResults.verifyThatProductIsAddedToCart();

	}

//	@Test(dataProvider = "getValidLoginData")
	public void editCartTest(HashMap<String, String> input) throws InterruptedException {
		// To be implemented
		LoginPage userLogin = homePage.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct("Iphone");
//		searchResults.displayOnlyInStockProducts();
		searchResults.verifyThatProductIsInResults();
		CartPage cartPage = searchResults.addProductToCart();
		searchResults.verifyThatProductIsAddedToCart();

		cartPage.goToCartPage();
		cartPage.editProductQuantity("2");
		cartPage.verifyThatProductQuantityGotUpdated();

	}

//	@Test(dataProvider = "getValidLoginData")
	public void editOrderDetailsInCheckoutTest(HashMap<String, String> input) throws InterruptedException {
		// To be implemented
		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct("Iphone");
		searchResults.verifyThatProductIsInResults();
		CartPage cartPage = searchResults.addProductToCart();
		searchResults.verifyThatProductIsAddedToCart();

		cartPage.goToCartPage();
		CheckoutPage checkout = cartPage.editProductQuantity("2");
		cartPage.verifyThatProductQuantityGotUpdated();
		cartPage.goToCheckoutPage();

//		checkout.acceptTermsAndConditions();
		checkout.proceedToConfirmationPage();

	}

	@Test(dataProvider = "endToEndPurchaseData", dataProviderClass = DataProviders.class)
	public void orderDetailsConfirmationTest(HashMap<String, String> input) throws InterruptedException {
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

	@Test(dataProvider = "orderHistoryData", dataProviderClass = DataProviders.class, dependsOnMethods = { "orderDetailsConfirmationTest" })
	public void orderHistoryTest(HashMap<String, String> input) throws InterruptedException, IOException {
		// To be implemented
		LoginPage userLogin = navigationBar.goToLoginPage();
		AccountPage accountPage = userLogin.login(input.get("email"), input.get("password"));
		OrderHistoryPage orderHistory = accountPage.goToOrderHistory();
		orderHistory.validateOrderHistoryDetails(input.get("customerName"), input.get("status"), input.get("totalAmount"),
				input.get("numberOfItems"));

//		OrderHistoryPage orderHistory = navigationBar.goToOderHistoryPage();
//		orderHistory.veryfyOrderHistory();

	}

}