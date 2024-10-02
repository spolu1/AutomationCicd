package sridharpolu.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import sridharpou.pageobject.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver intializeDriver() throws IOException {
		
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream("C:\\Eclipse\\TestNGPageObject\\src\\main\\java\\sridharpolu\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName= System.getProperty("browser")!=null? System.getProperty("browser") : prop.getProperty("browser");
	    //prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")){
	
					
		WebDriverManager.chromedriver().setup();		
		driver = new ChromeDriver();
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			
		} else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			
		} else if(browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
     public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to string
		
		String jsonContent=FileUtils.readFileToString(new File (filePath),
		       StandardCharsets.UTF_8);
		
		// String to HashMap Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
}
     
     public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
    	 
    	 TakesScreenshot ts = (TakesScreenshot)driver;
    	 File source = ts.getScreenshotAs(OutputType.FILE);
    	 File file = new File(System.getProperty("user.dir") + "\\reports\\"+ testcaseName+ ".png");
    	 FileUtils.copyFile(source, file);
    	 return System.getProperty("user.dir") + "\\reports\\"+ testcaseName+ ".png";
     }

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver =intializeDriver();
		landingPage =new LandingPage(driver);		
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
