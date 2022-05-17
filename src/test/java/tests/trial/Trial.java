package tests.trial;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class Trial {
    WebDriver driver;
    public ChromeOptions chromeOptions(){
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\Downloads");
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }
    @Test
    public void testDownload() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions());
        driver.get("https://the-internet.herokuapp.com/download");
        WebElement downloadLink = driver.findElement(By.linkText("webdriverIO.png"));
        downloadLink.click();
        Thread.sleep(2000);


    }

}
