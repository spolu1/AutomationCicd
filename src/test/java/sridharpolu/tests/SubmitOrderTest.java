package sridharpolu.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;
import sridharpolu.Abstractcomponent.AbstractComponentmodel;
import sridharpolu.Abstractcomponent.OrderPage;
import sridharpolu.TestComponents.BaseTest;
import sridharpou.pageobject.CartPage;
import sridharpou.pageobject.CheckOutPage;
import sridharpou.pageobject.ConfirmationPage;
import sridharpou.pageobject.LandingPage;
import sridharpou.pageobject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String ProductName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups ={"Purchase"})
	
	public void EcommersApp(HashMap<String,String> input ) throws IOException
	{
			
		
		ProductCatalogue productcatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProductToCard(input.get("ProductName"));
		CartPage cartPage = productcatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage=cartPage.goTocheckOut();		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.sumbitOrder();				
		String confirmMessage= confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDR."));
	 }
   @Test(dependsOnMethods= {"EcommersApp"})
	public void OrderHistoryTest() {
		ProductCatalogue productcatalogue=landingPage.loginApplication("sridharpolu@gmail.com", "Sree@123");
		OrderPage orderPage=productcatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(ProductName));
		
	}

@DataProvider
public Object[][] getData() throws IOException {

	List<HashMap<String,String>> data= getJsonDataToMap("C:\\Eclipse\\TestNGPageObject\\src\\test\\java\\sridharpolu\\data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
	   
}

}

//@DataProvider
//public Object[][] getData() {
//  
//  return new Object[][] {{"sridharpolu@gmail.com","Sree@123","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//}

//HashMap<String,String> map= new HashMap<String,String>();
//map.put("email", "sridharpolu@gmail.com");
//map.put("password", "Sree@123");
//map.put("ProductName", "ZARA COAT 3");
//
//HashMap<String,String> map1= new HashMap<String,String>();
//map1.put("email", "anshika@gmail.com");
//map1.put("password", "Iamking@000");
//map1.put("ProductName", "ADIDAS ORIGINAL");





