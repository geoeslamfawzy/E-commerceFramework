package tests;

import io.cucumber.java.BeforeAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;
import utilities.WindowManager;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class BaseTest {

    protected WebDriver driver;
    public ChromeOptions chromeOptions(){
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\Downloads");
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }

    @BeforeAll
    @Parameters({"browser"})
    public void openDrive(@Optional("chrome") String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            /*/System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir")+"/Drivers/chromedriver.exe");*/
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions());
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("ie") ||
                browserName.equalsIgnoreCase("internerexplorer")){
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir")+"/Drivers/IEDriverServer.exe");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.navigate().to("https://demo.nopcommerce.com/");
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result) throws IOException {
        if(result.getStatus()== ITestResult.FAILURE){
            System.out.println("The test has failed");
            System.out.println("Taking screenshot ........");
            Helper.captureScreenshot(driver, result.getName());
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void fluentWait(WebElement element){
        Wait<WebDriver> fwait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofMinutes(1)) //Wait for a minuit
                .pollingEvery(Duration.ofSeconds(1)) //Check the element every second
                .ignoring(NoSuchElementException.class); //ignore if you didn't find the element
        WebElement msg = fwait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return element;
            }
        });
    }

    /**public static FirefoxOptions firefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", downloadPath);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("pdfjs.disabled", true);
        return options;
    }*/

    public WindowManager getWindowManager(){
        return new WindowManager(driver);
    }

}
