package tests.DD;

import data.LoadProperties;
import tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class RegistrationTestWithPropertyFile extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    String firstName = LoadProperties.userData.getProperty("firstName");
    String lastName = LoadProperties.userData.getProperty("lastName");
    String mail = LoadProperties.userData.getProperty("mail");
    String password = LoadProperties.userData.getProperty("password");

    @Test(priority = 1)
    public void testSuccessfulRegistration() throws InterruptedException{
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        registerPage = new RegisterPage(driver);
        registerPage.register(firstName, lastName, mail, password);
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
        loginPage.login(mail, password);
        Assert.assertTrue(registerPage.logoutLink.isDisplayed());
        Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));
    }
}
