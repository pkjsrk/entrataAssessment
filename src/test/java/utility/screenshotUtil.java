package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class screenshotUtil {

	
	
	public static String captureScreenshot(WebDriver driver, String testName) {
	    TakesScreenshot ss = (TakesScreenshot) driver;
	    File src = ss.getScreenshotAs(OutputType.FILE);

	    String dir = "screenshots";
	    new File(dir).mkdirs(); // ensures folder exists

	    String dest = dir + "/" + testName.replaceAll("[^a-zA-Z0-9_-]", "_") + ".png";

	    try {
	        Files.copy(src.toPath(), Paths.get(dest));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return new File(dest).getAbsolutePath(); // return full path for Extent
	}

}
