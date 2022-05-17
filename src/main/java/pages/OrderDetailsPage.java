package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class OrderDetailsPage extends BasePage{
    public OrderDetailsPage(WebDriver driver){super(driver);}

    @FindBy(tagName = "h1")
    public WebElement pageTitle;

    @FindBy(css = "table.data-table td")
    List<WebElement> tableData;

    @FindBy(css = "a.button-2.print-order-button")
    WebElement printBtn;

    @FindBy(css = "a.button-2.pdf-invoice-button")
    WebElement pdfInvoice;

    public boolean isProductVisible(String product){
        for(WebElement data : tableData){
            String dataInTable = data.getText().toLowerCase(Locale.ROOT);
            if(dataInTable.contains(product.toLowerCase(Locale.ROOT))){
                System.out.println("The product is visible");
                return true;
            }
        }
        System.out.println("The product is not visible");
        return false;
    }
    public void getInvoice(){
        clickOn(pdfInvoice);
    }

    public void printOrder(){
        clickOn(printBtn);
    }
}
