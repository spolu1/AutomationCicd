package sridharpolu.stepDefinition;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sridharpou.pageobject.CartPage;
import sridharpou.pageobject.CheckOutPage;
import sridharpou.pageobject.ConfirmationPage;
import sridharpou.pageobject.LandingPage;
import sridharpou.pageobject.ProductCatalogue;

public class StepDefinitionImpl {

	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationPage;
	@Given ("I landed on Ecommerce Page")
	public void I_Landed_On_Ecommerce_Page() {
		
		landingPage = launchApplication();
	}
	
	 @Given ("^Login  with username (.+) and password (.+)$")
	 public void Login_with_Username_and_Password(String username,String password) {
		 
		  productcatalogue=landingPage.loginApplication(username, password);
	 }
	 
	 @When ("^I add product (.+) to cart$")
	 public void I_add_product_to_cart(String ProductName) {
		 List<WebElement> products= productcatalogue.getProductList();
			productcatalogue.addProductToCard(ProductName);
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void Checout_Submit_the_order(String ProductName) {
		    CartPage cartPage = productcatalogue.goToCartPage();
			Boolean match=cartPage.VerifyProductDisplay(ProductName);
			Assert.assertTrue(match);
			CheckOutPage checkoutPage=cartPage.goTocheckOut();		
			checkoutPage.selectCountry("india");
			confirmationPage=checkoutPage.sumbitOrder();
	  
	 }
	 
	 @Then("{string} message is displayed on Confirmation Page")
	 public void message_is_displayed_on_Cofirmation_Page(String string) {
		 
		 String confirmMessage= confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	 }
	 
	 
	 
}
