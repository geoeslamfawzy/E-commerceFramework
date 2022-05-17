package tests.DD;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import tests.BaseTest;

import java.io.IOException;

public class RegistrationWithFakeData extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String mail = faker.internet().emailAddress();
    String password = faker.number().digits(8).toString();


    @Test(priority = 1)
    public void testSuccessfulRegistration() throws IOException, ParseException {

        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        //1- Register
        registerPage = new RegisterPage(driver);
        registerPage.register(firstName, lastName, mail, password);
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));

        System.out.println("The Fake data is below");
        System.out.println("The first name  is "+firstName);
        System.out.println("The last name  is "+lastName);
        System.out.println("The mail is "+mail);
        System.out.println("The password is "+password);

        //Logout
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
        //Login
        loginPage = new LoginPage(driver);
        homePage.gotoLoginPage();
        loginPage.login(mail,password);
        Assert.assertTrue(registerPage.logoutLink.isDisplayed());
        Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));
        //Logout
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
    }
}
