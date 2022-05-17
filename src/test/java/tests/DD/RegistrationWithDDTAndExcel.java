package tests.DD;

import data.ExcelReader;
import tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.io.IOException;

public class RegistrationWithDDTAndExcel extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;

    @DataProvider(name = "excelData")
    public Object[][] registerDataFromExcel() throws IOException {
        //Get the data from Excel Reader Class
        ExcelReader excelReader = new ExcelReader();
        return excelReader.getExcelData();
    }

    @Test(priority = 1, dataProvider = "excelData")
    public void testSuccessfulRegistration(String firstName, String lastName, String mail, String password){
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        registerPage = new RegisterPage(driver);
        registerPage.register(firstName, lastName, mail, password);

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
    }

}
