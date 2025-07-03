package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import hooks.baseTest;
import pages.homePage;
import pages.watchDemoPage;
import utility.configReader;

public class demoFormTest extends baseTest {

    private homePage homeObj;
    private watchDemoPage demoObj;

    @Test(priority = 1)
    public void VerifyHomePageTitle() throws IOException {
        String siteUrl = configReader.getProperty("URL");
        String expectedTitle = configReader.getProperty("pageTitle");

        driver.get(siteUrl);
        log.info("Navigated to: " + siteUrl);

        String actualTitle = driver.getTitle();
        log.info("Actual page title: " + actualTitle);

        assertEquals(actualTitle, expectedTitle, "Page title mismatch!");
    }
    
    
    @Test(priority = 2)
    public void VerifySolutionsOptions() throws IOException {
    	homeObj = new homePage(driver);
    	 
        String optionToVerify = configReader.getProperty("solutionOptionToVerify");

        homeObj.hoverOverSolutionsMenu();
        
        List<String> solutionsOptions = homeObj.fetchSolutionsMenuOption();

        log.info("Available solution options: " + solutionsOptions);

        boolean isOptionPresent = solutionsOptions.contains(optionToVerify);

        assertTrue(isOptionPresent, optionToVerify + " is not available in the Solutions menu.");
        
        log.info("Verified option is present: " + optionToVerify);
    }

    @Test(priority = 3)
    public void VerifyNavigationToWatchDemoPage() {
        homeObj = new homePage(driver);
        demoObj = new watchDemoPage(driver);

        homeObj.clickWatchDemoButton();
        log.info("Clicked on Watch Demo button");

        boolean logoDisplayed = demoObj.verifyLogo();
        assertTrue(logoDisplayed, "Entrata logo not displayed on Watch Demo page");
    }

    @Test(priority = 4)
    public void VerifyTooltipIsDisplayedForInvalidPhone() throws IOException {
        demoObj = new watchDemoPage(driver);

        String invalidPhone = configReader.getProperty("invalidPhone");
        String lastname = configReader.getProperty("lastname");
        String expectedTooltip = configReader.getProperty("invalidTooltipMsgForEmail");

        demoObj.enterPhone(invalidPhone);
        demoObj.enterLastName(lastname);
        demoObj.clickPhoneField();

        String actualTooltip = demoObj.verifyPhoneTooltip();
        log.info("Tooltip message shown: " + actualTooltip);

        assertTrue(actualTooltip.contains(expectedTooltip),
            "Expected tooltip message not displayed. Expected: [" + expectedTooltip + "], Got: [" + actualTooltip + "]");
    }

    @Test(priority = 5)
    public void VerifyDropdownOptionSelectionInDropdown() throws IOException {
        demoObj = new watchDemoPage(driver);

        String expectedOption = configReader.getProperty("dropdownOptionToSelect");

        String actualOption = demoObj.verifyUnitsSelection(expectedOption);
        log.info("Dropdown selection: Expected: " + expectedOption + ", Actual: " + actualOption);

        assertEquals(actualOption, expectedOption, "Dropdown selection mismatch.");
    }
}
