package tests.DD;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import tests.BaseTest;

import java.io.FileReader;
import java.io.IOException;

public class RegistrationWithCSV extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;

    CSVReader csvReader;

    @Test(priority = 1)
    public void testSuccessfulRegistration() throws IOException, CsvValidationException {
        //Get the path of CSV file
        String csvPath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\userData.csv";
        csvReader = new CSVReader(new FileReader(csvPath));

        String csvCell[];

        //to read from the file, maka a while loop to execute until the end of the file
        while ((csvCell = csvReader.readNext()) != null){
            String firstName = csvCell[0];
            String lastName = csvCell[1];
            String mail = csvCell[2];
            String password = csvCell[3];

            homePage = new HomePage(driver);
            homePage.gotoRegisterPage();
            //1- Registare
            registerPage = new RegisterPage(driver);
            registerPage.register(firstName, lastName, mail, password);
            Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));
            //Logout
            registerPage.logout();
            Assert.assertTrue(homePage.loginLink.isDisplayed());
            Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
            //Login
            loginPage = new LoginPage(driver);
            homePage.gotoLoginPage();
            loginPage.login(mail, password);
            Assert.assertTrue(registerPage.logoutLink.isDisplayed());
            Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));
            //Logout
            registerPage.logout();
            Assert.assertTrue(homePage.loginLink.isDisplayed());
            Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
        }

    }
}
