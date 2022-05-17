package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver){super(driver);}

    @FindBy(id = "gender-male")
    WebElement maleBtn;

    @FindBy(id ="FirstName")
    WebElement fnameField;

    @FindBy(id = "LastName")
    WebElement lnameField;

    @FindBy(id = "Email")
    WebElement emailField;

    @FindBy(id = "Password")
    WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    WebElement registerBtn;

    @FindBy(css = "div.result")
    public WebElement confirmationMsg;

    @FindBy(css = "a.ico-logout")
    public WebElement logoutLink;


    public void register(String firstName, String lastName, String email, String password){
        clickOn(maleBtn);
        writeText(fnameField, firstName);
        writeText(lnameField, lastName );
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        registerBtn.click();
    }

    public void logout(){
        clickOn(logoutLink);
    }
}
