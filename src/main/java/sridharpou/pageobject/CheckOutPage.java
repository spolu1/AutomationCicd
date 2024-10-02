package sridharpou.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sridharpolu.Abstractcomponent.AbstractComponentmodel;

public class CheckOutPage extends AbstractComponentmodel {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy (css ="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy (css ="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectcountry;	
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName){
		
		Actions a = new Actions(driver);
		 a.sendKeys(country, countryName).build().perform();
		 waitForElementToAppear(results);
		 selectcountry.click();
		
	}
	
	public ConfirmationPage sumbitOrder() {
		
		submit.click();
		return new ConfirmationPage(driver);
	}		
		
		
	}
	








