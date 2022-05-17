package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class AddToCartPage extends BasePage{
    public AddToCartPage(WebDriver driver){super(driver);}

    @FindBy(tagName = "h1")
    public WebElement pageTitle;

    @FindBy(css = "div.no-data")
    public WebElement emptyCartMsg;

    @FindBy(css = "button.remove-btn")
    List<WebElement> removeItemFromCart;

    @FindBy(css = "a.product-name")
    List<WebElement> productNamesInCart;

    @FindBy(id = "termsofservice")
    WebElement termsOfServiceCheckBox;

    @FindBy(id = "checkout")
    WebElement  checkoutBtn;

    //This Method to remove a specific product from the cart
    public void removeProductFromCart(int productNo){
        clickOn(removeItemFromCart.get(productNo-1));
    }
    //This method to remove the product from the cart
    //All you have to do is to give the method the product name
    public void removeProductFromCart(String productName){
        int i=0;
        for(WebElement productNameInCart : productNamesInCart){
            i++;
            if (productNameInCart.getText().toLowerCase(Locale.ROOT)
                    .contains(productName.toLowerCase(Locale.ROOT))){
                clickOn(removeItemFromCart.get(i-1));
                break;
            }
        }
    }

    public void gotoCheckoutPage(){
        clickOn(termsOfServiceCheckBox);
        clickOn(checkoutBtn);
    }
}
