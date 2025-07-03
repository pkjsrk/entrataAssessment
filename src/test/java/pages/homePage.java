package pages;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class homePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//constructor for initilizing object
	public homePage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	
	//Below are the locators specific to page.
	
	@FindBy(xpath = "//a[contains(normalize-space(text()),'Watch demo')]")
	WebElement watchDemoButton;
	
	
	@FindBy(xpath = "//div[@id='w-dropdown-toggle-1']")
	WebElement solutionsMenu;
	
	@FindBy(xpath = "//div[contains(@class,'list is-solutions')]//div[contains(@class,'link-heading')]")
	List<WebElement> solutionMenuOptions;
	
	//Below are the action methods
	
	public void clickWatchDemoButton() {
		wait.until(ExpectedConditions.visibilityOf(watchDemoButton)).click();
	}
	
	
	public void hoverOverSolutionsMenu() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    try {
	        wait.until(ExpectedConditions.visibilityOf(solutionsMenu));
	        Actions act = new Actions(driver);
	        act.moveToElement(solutionsMenu).perform();
	    } catch (Exception e) {
	        throw e;
	    }
	}

	
	public List<String> fetchSolutionsMenuOption() {
	    wait.until(ExpectedConditions.visibilityOfAllElements(solutionMenuOptions));

	    List<String> optionTexts = new ArrayList<>();
	    for (WebElement option : solutionMenuOptions) {
	        optionTexts.add(option.getText().trim());
	    }
	    return optionTexts;
	}
	
	
	
	

}
