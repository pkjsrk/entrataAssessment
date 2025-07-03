package hooks;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.screenshotUtil;

public class baseTest {

    protected WebDriver driver;
    protected static Logger log;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        log = LogManager.getLogger(baseTest.class);
        log.info("Extent report setup complete");
    }

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        log.info("WebDriver initialized for test class");
    }

    @BeforeMethod
    public void startTest(Method method) {
        test = extent.createTest(method.getName());
        log.info("Started test: " + method.getName());
    }

    @AfterMethod
    public void logTestResult(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                try {
                    String path = screenshotUtil.captureScreenshot(driver, result.getName());
                    test.fail("Test failed: " + result.getThrowable(),
                            MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                } catch (Exception e) {
                    log.error("Failed to capture or attach screenshot", e);
                    test.fail("Test failed, and screenshot capture failed: " + result.getThrowable());
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test passed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("Test skipped");
            }
        } catch (Exception e) {
            log.error("Unexpected error in logTestResult: ", e);
        }
    }


    @AfterClass
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
            log.info("WebDriver session quit after test class");
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
        log.info("Extent report flushed");
    }
}
