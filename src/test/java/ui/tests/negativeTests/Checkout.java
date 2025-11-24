package ui.tests.negativeTests;

import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.dataProviders.NegativeTestDataProviders;
import ui.pages.CartPage;
import ui.pages.CheckoutPage;
import ui.pages.LoginPage;
import ui.pages.SearchResultsPage;

public class Checkout extends BaseTest {

	@Test(dataProvider = "negativeCheckoutDataProvider", dataProviderClass = NegativeTestDataProviders.class)
	public void negativeCheckoutTest(java.util.HashMap<String, String> input) throws InterruptedException {

		LoginPage userLogin = navigationBar.goToLoginPage();
		userLogin.login(input.get("email"), input.get("password"));
		SearchResultsPage searchResults = navigationBar.searchProduct(input.get("productName"));
		searchResults.displayOnlyInStockProducts(input.get("availability"));
		searchResults.verifyThatProductIsInResults();
		CartPage cartPage = searchResults.addProductToCart();
		searchResults.verifyThatProductIsAddedToCart();

		cartPage.goToCartPage();
		CheckoutPage checkout = cartPage.editProductQuantity(input.get("productQuantity"));

		cartPage.goToCheckoutPage();

		checkout.fillInBillingDetails(input.get("firstName"), input.get("lastName"), input.get("company"),
				input.get("address1"), input.get("address2"), input.get("city"), input.get("postCode"),
				input.get("country"), input.get("region"));

		checkout.acceptTermsAndConditions(input.get("agreeToTerms"));
		checkout.proceedToConfirmationPage();
		checkout.verifyErrorMessageForMandatoryBillingDetails(input.get("expected"));
		checkout.removeItemFromCheckout();
	}

}
