package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.*;

import org.testng.annotations.Test;

import hooks.baseTest;
import pages.homePage;
import utility.configReader;

public class demoFormTest extends baseTest {
	
	
	homePage homeObj;
	
	
	
	//Below testcase is to verify Homepage title
	
	@Test(priority=1)
	public void verifyHomePageTitle() throws IOException {
		
		String siteUrl = configReader.getProperty("URL");
		String expectedPageTitle = configReader.getProperty("pageTitle");
				
		driver.get(siteUrl);
		String actualPageTitle = driver.getTitle();
		
		assertEquals(actualPageTitle, expectedPageTitle);
				
	}
	
	
	//Below testcase depends on 1st method and is for navigation to Watch demo page
	@Test(dependsOnMethods = "verifyHomePageTitle")
	public void navigationToWatchDemoPage() {
		
		homeObj = new homePage(driver);
		
		homeObj.clickWatchDemoButton();
		
		
		Set<String> windowHandle = driver.getWindowHandles();
		
		for(String handle : windowHandle) {
			
			driver.switchTo().window(handle);
		}
		
		
		
	}

}
