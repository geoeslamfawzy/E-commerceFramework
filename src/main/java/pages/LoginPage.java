package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){super(driver);}

    @FindBy(css = "input.email")
    WebElement mailField;

    @FindBy(id = "Password")
    WebElement passwordField;

    @FindBy(css = "button.button-1.login-button")
    WebElement submitBtn;

    public void login(String mail, String password){
        writeText(mailField, mail);
        writeText(passwordField, password);
        clickOn(submitBtn);
    }
}
