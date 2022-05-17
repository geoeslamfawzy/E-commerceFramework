package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.RegisterPage;
import tests.BaseTest;

public class MyStepdefs extends BaseTest{
    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String mail = faker.internet().emailAddress();
    String password = faker.number().digits(8).toString();

    @Given(": User in the home page")
    public void userInTheHomePage() {
       /* WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://demo.nopcommerce.com/");*/
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
    }

    @When(": I Click on the register link")
    public void iClickOnTheRegisterLink() {
        Assert.assertTrue(driver.getCurrentUrl().contains("register"));
    }

    @And("I entered the user data")
    public void iEnteredTheUserData() {
        registerPage = new RegisterPage(driver);
        registerPage.register(firstName, lastName, mail, password);

        System.out.println("The Fake data is below");
        System.out.println("The first name  is "+firstName);
        System.out.println("The last name  is "+lastName);
        System.out.println("The mail is "+mail);
        System.out.println("The password is "+password);
    }

    @Then(": The registration complete successfully and moves the user to the home page")
    public void theRegistrationCompleteSuccessfullyAndMovesTheUserToTheHomePage() {
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
    }
}
