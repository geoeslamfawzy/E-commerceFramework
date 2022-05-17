package tests.DD;

import com.opencsv.CSVReader;
import data.JsonDataReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import tests.BaseTest;

import java.io.IOException;

public class RegistrationWithJSON extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;

    CSVReader csvReader;

    @Test(priority = 1)
    public void testSuccessfulRegistration() throws IOException, ParseException {
        JsonDataReader jsonDataReader = new JsonDataReader();
        jsonDataReader.readJson("userData.json");

        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        //1- Registare
        registerPage = new RegisterPage(driver);
        registerPage.register(jsonDataReader.firstName, jsonDataReader.lastName,
                jsonDataReader.mail, jsonDataReader.password);
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));
        //Logout
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
        //Login
        loginPage = new LoginPage(driver);
        homePage.gotoLoginPage();
        loginPage.login(jsonDataReader.mail, jsonDataReader.password);
        Assert.assertTrue(registerPage.logoutLink.isDisplayed());
        Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));
        //Logout
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
    }
}
