package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    protected static WebDriver driver;
    protected static JavascriptExecutor jse;
    protected static Select select;
    protected static Actions act;
    public BasePage(WebDriver driver){     //Make constructor to this page
        PageFactory.initElements(driver, this);
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);
    }


    protected static void clickOn(WebElement element){
        element.click();
    }

    protected static void doubleClickOn(WebElement element){
        act.doubleClick(element);
    }

    protected static void writeText(WebElement field, String text){
        field.clear();
        field.sendKeys(text);
    }

    public void scrollDown(){
        jse.executeScript("window.scrollBy(0, 2500)");
    }
    public void scrollUp(){
        jse.executeScript("window.scrollBy(0, 0)");
    }



}
