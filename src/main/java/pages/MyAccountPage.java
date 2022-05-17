package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    public MyAccountPage(WebDriver driver){
        super(driver);
    }
    @FindBy(linkText="Change password")
    WebElement changePasswordLink;

    @FindBy(id = "OldPassword")
    WebElement oldPasswordField;

    @FindBy(id = "NewPassword")
    WebElement newPasswordField;

    @FindBy(id = "ConfirmNewPassword")
    WebElement confirmNewPasswordField;

    @FindBy(css = "button.button-1.change-password-button")
    WebElement changePasswordBtn;

    @FindBy(css = "p.content")
    public WebElement changePasswordMsg;

    @FindBy(css = "span.close")
    WebElement msgCloseSymbol;

    public void changePassword(String oldPassword, String newPassword){
        clickOn(changePasswordLink);
        writeText(oldPasswordField, oldPassword);
        writeText(newPasswordField, newPassword);
        writeText(confirmNewPasswordField, newPassword);
        clickOn(changePasswordBtn);
    }
    public void closeSuccessfulRegisterMsg(){
        clickOn(msgCloseSymbol);
    }

}
