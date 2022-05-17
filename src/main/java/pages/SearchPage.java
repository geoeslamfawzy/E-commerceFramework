package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver driver){super(driver);}

    @FindBy(id = "small-searchterms")
    WebElement searchField;

    @FindBy(css = "button.button-1.search-box-button")
    WebElement searchBtn;

    @FindBy(id = "ui-id-1")
    List<WebElement> searchListSuggest;

    public void searchForProduct(String product){
        writeText(searchField, product);
        clickOn(searchBtn);
    }

    public void searchByAutoSuggest(String product){
        writeText(searchField, product);
        clickOn(searchListSuggest.get(0));
    }
}
