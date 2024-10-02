package sridharpou.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sridharpolu.Abstractcomponent.AbstractComponentmodel;

public class CartPage extends AbstractComponentmodel{
	
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy (css =".cartSection h3")
	private List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean VerifyProductDisplay(String ProductName) {
		
		Boolean match=cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

	public CheckOutPage goTocheckOut() {
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
	
}
