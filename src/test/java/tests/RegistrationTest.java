package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class RegistrationTest extends BaseTest{
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;

    @Test(priority = 1)
    public void testSuccessfulRegistration() throws InterruptedException{
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        Thread.sleep(2000);
        registerPage = new RegisterPage(driver);
        registerPage.register("Eslam", "Fawzy", "eslam33@gmail.com", "12345678");
        Thread.sleep(2000);
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));
    }

    @Test(dependsOnMethods = {"testSuccessfulRegistration"})
    public void testLogout(){
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
    }

    @Test(dependsOnMethods = {"testLogout"})
    public void loginWithRegistaredMail(){
        loginPage = new LoginPage(driver);
        homePage.gotoLoginPage();
        loginPage.login("eslsaam1225@gmail.com", "12345678");
        Assert.assertTrue(registerPage.logoutLink.isDisplayed());
        Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));
    }
}
