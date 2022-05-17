package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage extends BasePage{
    public WishlistPage(WebDriver driver){super(driver);}

    @FindBy(tagName = "h1")
    public WebElement wishlistTitle;

    @FindBy(xpath = "//a[contains(text(), 'Mac')]")
    public WebElement productInWishlist;

    @FindBy(name = "addtocart")
    WebElement addToCartCheckbox;

    @FindBy(name = "addtocartbutton")
    WebElement addToCartBtn;

    @FindBy(id = "updatecart")
    WebElement updateWishlistBtn;

    public void addWishlistIntoCart(){
        clickOn(addToCartCheckbox);
        clickOn(addToCartBtn);
    }
}
