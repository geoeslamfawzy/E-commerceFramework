package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.HomePage;
import pages.RegisterPage;

import java.time.Duration;

public class ChangePasswordTest extends BaseTest{
    HomePage homePage;
    RegisterPage registerPage;
    MyAccountPage myAccountPage;
    LoginPage loginPage;

    String firstName = "Eslam";
    String lastName = "Fawzy";
    String mail = "eslam34@fawzy.com";
    String password = "12345678";
    String newPassword = "24233291";
    @Test(priority = 1)
    public void testSucessfulRegistration() throws InterruptedException{
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        Thread.sleep(2000);
        registerPage = new RegisterPage(driver);
        registerPage.register(firstName, lastName, mail, password);
        Thread.sleep(2000);
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));
    }



    @Test(priority = 2, dependsOnMethods = "testSucessfulRegistration")
    public void testChangePassword(){
        myAccountPage = new MyAccountPage(driver);
        homePage.gotoMyAccountPage();
        myAccountPage.changePassword(password, newPassword);
        Assert.assertTrue(myAccountPage.changePasswordMsg.getText().contains("Password was changed"));
        myAccountPage.closeSuccessfulRegisterMsg();
    }

    @Test(priority = 3)
    public void testLogout(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofMinutes(1));
        explicitWait.until(ExpectedConditions.elementToBeClickable(registerPage.logoutLink));
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
    }

    @Test(dependsOnMethods = {"testLogout"}, priority = 4)
    public void loginWithNewPassword(){
        loginPage = new LoginPage(driver);
        homePage.gotoLoginPage();
        loginPage.login(mail, newPassword);
        Assert.assertTrue(registerPage.logoutLink.isDisplayed());
        Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));
    }


}
