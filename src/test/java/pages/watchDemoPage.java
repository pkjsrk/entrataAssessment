package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class watchDemoPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	//constructor for initilizing object
	public watchDemoPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}
	
	
	//Below are the locators specific to page.
	
	@FindBy(xpath = "//div[@class='padding-global']//div[contains(@class,'logo_component')]")
	WebElement entrataLogo;
	
	
	@FindBy(xpath = "//input[@id='Phone']")
	WebElement phoneField;
	
	
	@FindBy(xpath = "//input[@id='LastName']")
	WebElement lastName;
	
	
	@FindBy(xpath = "//div[@id='ValidMsgPhone']")
	WebElement phoneToolTip;
	
	
	@FindBy(xpath = "//select[@id='Unit_Count__c']")
	WebElement dropdown;
	
	
	
	
	//Below are the action methods
	
	public void switchDriverToWatchDemo(){
		Set<String> windowHandle = driver.getWindowHandles();
		
		for(String handle : windowHandle) {
			
			driver.switchTo().window(handle);
		}
	}
	
	
	public boolean verifyLogo() {
		
		return entrataLogo.isDisplayed();
	}
	
	public void enterPhone(String invalidEmail) {
		wait.until(ExpectedConditions.visibilityOf(phoneField)).sendKeys(invalidEmail);
	}
	
	
	public void enterLastName(String lastname) {
		wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lastname);
	}
	
	
	public void clickPhoneField() {
		wait.until(ExpectedConditions.visibilityOf(phoneField)).click();
	}
	
	
	public String verifyPhoneTooltip() {
		return wait.until(ExpectedConditions.visibilityOf(phoneToolTip)).getText();
	}

	public String verifyUnitsSelection(String option) {
		
		Select opt = new Select(dropdown);
		opt.selectByVisibleText(option);
		
		return wait.until(ExpectedConditions.visibilityOf(dropdown)).getText();
		
	}
}
