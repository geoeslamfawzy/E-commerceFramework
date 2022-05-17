package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddReviewPage extends BasePage{
    public AddReviewPage(WebDriver driver){super(driver);}

    @FindBy(id = "AddProductReview_Title")
    WebElement reviewTitle;

    @FindBy(id = "AddProductReview_ReviewText")
    WebElement reviewText;

    @FindBy(css = "div.rating-options>input")
    List<WebElement> ratingOptions;

    @FindBy(name = "add-review")
    WebElement submitReviewBtn;

    @FindBy(css = "div.result")
    public WebElement msgAfterReviewSubmit;

    @FindBy(css = "div.page-title a")
    WebElement itemMainLink;

    public void addReview(String reviewTitle, String reviewText, int rate){
        writeText(this.reviewTitle, reviewTitle);
        writeText(this.reviewText, reviewText);
        if(rate>=5){
            clickOn(ratingOptions.get(5));
        }
        else if(rate<=1){
            clickOn(ratingOptions.get(1));
        }
        else {
            clickOn(ratingOptions.get(rate));
        }
        clickOn(submitReviewBtn);
    }

    public void returnToProductPage(){
        clickOn(itemMainLink);
    }

}
