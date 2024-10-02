package sridharpou.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sridharpolu.Abstractcomponent.AbstractComponentmodel;

public class ProductCatalogue extends AbstractComponentmodel {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy (css =".ng-animating")
	WebElement spinner;
	
	By productsBY=By.cssSelector(".mb-3");
	By addProductToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");


   public List<WebElement> getProductList()
{
	   waitForElementToAppear(productsBY);
	   return products;
	
}
   public WebElement getProductByName(String ProductName) {
	   
	   WebElement prod= products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	   return prod;
   }
   
   public void addProductToCard(String ProductName) {
	   
	   WebElement prod= getProductByName(ProductName);
       prod.findElement(addProductToCart).click();
       waitForElementToAppear(toastMessage);
       waitForElementToDessapear(spinner);
   }
}








