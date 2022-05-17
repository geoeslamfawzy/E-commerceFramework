package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage{
    public ContactUsPage(WebDriver driver){super(driver);}

    @FindBy(css = "input.fullname")
    WebElement fullname;

    @FindBy(css = "input.email")
    WebElement mail;

    @FindBy(css="textarea.enquiry")
    WebElement enquiryMsg;

    @FindBy(css="button.button-1.contact-us-button")
    WebElement submitBtn;

    @FindBy(css = "div.result")
    public WebElement msgAfterEnquiry;

    public void makeInquiry(String fullname, String mail, String enquiry)
    {
        writeText(this.fullname, fullname);
        writeText(this.mail, mail);
        writeText(enquiryMsg, enquiry);
        clickOn(submitBtn);
    }
}
