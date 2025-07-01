package hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	public WebDriver driver; 
	
	//setting up chromedriver using webDriverManager
	@BeforeClass
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions chrome  = new ChromeOptions();
		driver = new ChromeDriver(chrome);		
		driver.manage().window().maximize();
	}
	
	
	
	//Teardown method to quit the session
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
