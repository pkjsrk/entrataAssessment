package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class homePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	public homePage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	
	
	@FindBy(xpath = "//div[@class='nav_buttons']//a[contains(normalize-space(text()),'Watch demo')]")
	WebElement watchDemoButton;
	
	
	public void clickWatchDemoButton() {
		wait.until(ExpectedConditions.visibilityOf(watchDemoButton)).click();
	}
	
	
	
	
	

}
