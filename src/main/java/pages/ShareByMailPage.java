package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShareByMailPage extends BasePage {
    public ShareByMailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css =  "input.friend-email")
    WebElement friendMail;

    @FindBy(name = "YourEmailAddress")
    WebElement myMail;

    @FindBy(css="textarea.your-email")
    WebElement personalMsg;

    @FindBy(css="button.button-1.send-email-a-friend-button")
    WebElement submitBtn;

    public void shareProductByMail(String friendMail, String myMail, String personalMsg){
        writeText(this.friendMail, friendMail);
        writeText(this.myMail, myMail);
        writeText(this.personalMsg, personalMsg);
        clickOn(submitBtn);
    }
}
