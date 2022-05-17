package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){super(driver);}

    @FindBy(linkText = "Register")
    WebElement registerLink;

    @FindBy(css = "a.ico-login")
    public WebElement loginLink;

    @FindBy(css = "a.ico-account")
    WebElement myAccountLink;


    @FindBy (linkText = "Contact us")
    WebElement contactUslink;

    @FindBy(css = "button.button-2.email-a-friend-button")
    WebElement sendMailButton;

    @FindBy(id = "customerCurrency")
    WebElement currencyDropDownList;

    @FindBy(linkText = "Computers")
    WebElement computerMenu;

    @FindBy(linkText = "Notebooks")
    WebElement noteBooksMenue;


    public void gotoRegisterPage(){
        clickOn(registerLink);
    }

    public void gotoLoginPage(){
        clickOn(loginLink);
    }

    public void gotoMyAccountPage(){
        clickOn(myAccountLink);
    }

    public void gotoContactUsPage()throws InterruptedException{
        scrollDown();
        Thread.sleep(2000);
        clickOn(contactUslink);
    }

    public void gotoSendMailPage(){
        clickOn(sendMailButton);
    }

    public void changeCurrency(String currency){
        select = new Select(currencyDropDownList);
        select.selectByVisibleText(currency);
    }

    public void selectNoteBooksMenue(){
        act.moveToElement(computerMenu)
                .moveToElement(noteBooksMenue)
                .click()
                .build()
                .perform();
    }


}
