package sridharpolu.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import sridharpolu.TestComponents.BaseTest;
import sridharpou.pageobject.ProductCatalogue;

public class ErrorValidationTest extends BaseTest{

	
	@Test(groups={"ErrorHandling"})
	
	public void EcommersApp() throws IOException
	{
			
		String ProductName = "ZARA COAT 3";
		landingPage.loginApplication("sridharpolu@gmail.com", "Sree@123");
		Assert.assertEquals("Incorrect Email or Password.",landingPage.getErrorMessage()); 	

	}

}
