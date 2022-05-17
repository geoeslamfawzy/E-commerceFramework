package tests.DD;

import tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class RegistrationTestWithDataProvider extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;

    @DataProvider(name = "testData")
    public static Object[][] userDataRegistration(){
            return new Object[][]{
                    {"Eslam", "Fawzy", "eslam2@fawzy.com", "12345678"},
                    {"Eslam", "Fawzy", "eslam3@fawzy.com", "12345678"},
                    {"Eslam", "Fawzy", "eslam4@fawzy.com", "12345678"}
            };
    }

    @Test(priority = 1, dataProvider = "testData")
    public void testSuccessfulRegistration(String fName, String lName, String mail, String password) {
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        registerPage = new RegisterPage(driver);
        registerPage.register(fName, lName, mail, password);
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));

        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));

        loginPage = new LoginPage(driver);
        homePage.gotoLoginPage();
        loginPage.login(mail, password);
        Assert.assertTrue(registerPage.logoutLink.isDisplayed());
        Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));

        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
    }
}
