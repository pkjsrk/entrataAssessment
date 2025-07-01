package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import hooks.baseTest;
import pages.homePage;
import pages.watchDemoPage;
import utility.configReader;

public class demoFormTest extends baseTest {
	
	
	homePage homeObj = new homePage(driver);
	watchDemoPage demoObj  = new watchDemoPage(driver);
	
	
	
	//Below test is to verify Homepage title
	
	@Test(priority=1)
	public void verifyHomePageTitle() throws IOException {
		
		String siteUrl = configReader.getProperty("URL");
		String expectedPageTitle = configReader.getProperty("pageTitle");
				
		driver.get(siteUrl);
		String actualPageTitle = driver.getTitle();
		
		assertEquals(actualPageTitle, expectedPageTitle);
				
	}
	
	
	//Below test depends on 1st method and is for navigation to Watch demo page
	
	@Test(dependsOnMethods = "verifyHomePageTitle")
	public void verifyNaviagationToWatchDemoPage() {
		
		homeObj = new homePage(driver);
		demoObj = new watchDemoPage(driver);
		
		
		homeObj.clickWatchDemoButton();		
	
		boolean logoVisibility = demoObj.verifyLogo();
		
		assertTrue(logoVisibility, "Verification: failed to display entrata logo.");
		
				
	}
	
	//Below test is to validate the error popup, when we enter wrong phone number
	
	@Test(dependsOnMethods = "verifyNaviagationToWatchDemoPage")
	public void verifyTooltipIsDisplayedForInvalidPhone() throws IOException {
		
		String invalidPhone = configReader.getProperty("invalidPhone");
		String lastname = configReader.getProperty("lastname");
		String expectedTooltipMsg = configReader.getProperty("invalidTooltipMsgForEmail");
		

		demoObj.clickPhoneField();
		demoObj.enterPhone(invalidPhone);
		demoObj.enterLastName(lastname);
		demoObj.clickPhoneField();
		
		
		String actualTooltip = demoObj.verifyPhoneTooltip();
		
		if(expectedTooltipMsg.contains(actualTooltip)) {
			assertTrue(true,expectedTooltipMsg+" is not displayed");
		}
		
	}
	
	
	//Below test is to verify correct toolTip is selected from dropdown
	
	@Test(dependsOnMethods = "verifyTooltipIsDisplayedForInvalidPhone")
	public void verifyDropdownOptionSelectionInDropdown() throws IOException {
		
		String expectedDropdownOption = configReader.getProperty("dropdownOptionToSelect");
		
		String actualDropdownOption = demoObj.verifyUnitsSelection(expectedDropdownOption);
		
		if(expectedDropdownOption.contains(actualDropdownOption)) {
			assertTrue(true,expectedDropdownOption+" is not displayed");
		}
				
	}

}
