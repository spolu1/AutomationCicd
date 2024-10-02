package sridharpolu.Abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sridharpou.pageobject.CartPage;

public class AbstractComponentmodel {
	
	
	WebDriver driver;
	
	
	public AbstractComponentmodel(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[routerlink='/dashboard/cart']")
	WebElement cardHeader;
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBY) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBY) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBY));
		
	}
	public CartPage goToCartPage() {
		
		cardHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
    public OrderPage goToOrderPage() {
		
		cardHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
 	
	public void waitForElementToDessapear(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	 
}

