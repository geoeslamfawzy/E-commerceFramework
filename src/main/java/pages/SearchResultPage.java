package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage{
    public SearchResultPage(WebDriver driver){super(driver);}

    @FindBy(css = "div.product-name>h1")
    public WebElement productTitleAfterSearch;

    @FindBy(css = "h2.product-title>a")
    WebElement productAfterSearch;

    @FindBy(id = "price-value-4")
    public WebElement productPriceLabel;

    @FindBy(linkText = "Add your review")
    WebElement addReviewLink;

    @FindBy(css = "div.product-review-links > a:nth-child(1)")
    WebElement reviewsLink;

    @FindBy(id = "add-to-wishlist-button-4")
    WebElement addWishListBtn;

    @FindBy(css = "p.content")
    public WebElement confirmAddMsg;

    @FindBy(css = "span.close")
    WebElement wishlistMsgCloseIcon;

    @FindBy(linkText = "wishlist")
    WebElement wishListPageLink;

    @FindBy(css = "span.wishlist-qty")
    WebElement noOfWishlists;

    @FindBy(css = "button.button-2.add-to-compare-list-button")
    WebElement addToCompareBtn;

    @FindBy(linkText = "product comparison")
    WebElement productComparisonPageLink;

    @FindBy(id = "add-to-cart-button-4")
    WebElement addToCartBtn;

    @FindBy(css = "a.ico-cart")
    WebElement addToCartPageLink;





    public void clickOnProduct(){
        clickOn(productAfterSearch);
    }

    public void gotoAddReview(){
        clickOn(addReviewLink);
    }

    public int getNoOfReviews(){
        String reviews = reviewsLink.getText();
        return Integer.parseInt(reviews.replaceAll("[\\D]", ""));
    }

    public void addWishList(){
        clickOn(addWishListBtn);
    }
    public void closeMsg(){
        clickOn(wishlistMsgCloseIcon);
    }

    public void gotoWishListPage(){
        clickOn(wishListPageLink);
    }
    public int getNoOfWishlists(){
        String wishlists = noOfWishlists.getText();
        scrollUp();
        return Integer.parseInt((wishlists.replaceAll("[\\D]", "")));
    }
    public void addToCompare(){
        clickOn(addToCompareBtn);
    }
    public void gotoComparisonPage(){
        clickOn(productComparisonPageLink);
    }

    public void addProductToCart(){
        clickOn(addToCartBtn);
    }

    public void gotoAddToCartPage(){
        clickOn(addToCartPageLink);
    }

    public int getNoOfItemsInCart(){
        String itemsNo = addToCartPageLink.getText();
        scrollUp();
        return Integer.parseInt((itemsNo.replaceAll("[\\D]", "")));
    }
}
