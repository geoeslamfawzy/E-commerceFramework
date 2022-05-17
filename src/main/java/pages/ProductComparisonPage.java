package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductComparisonPage extends BasePage{
    public ProductComparisonPage(WebDriver driver){super(driver);}
    @FindBy(tagName = "h1")
    public WebElement pageTitle;

    @FindBy(css = "a.clear-list")
    WebElement clearListBtn;

    @FindBy(css = "table.compare-products-table tr")
    List<WebElement> tableRaws;

    @FindBy(tagName = "td")
    List<WebElement> tableData;

    @FindBy(css = "div.no-data")
    public WebElement noDataMsg;


    public void compareProducts(){
        //Get all rows
        System.out.println(tableRaws.size());

        for(WebElement row : tableData ){
            System.out.println(row.getText() + "\t\t\t");
        }
    }

    public void clearCompareList(){
        clickOn(clearListBtn);
    }


}
