package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.WindowManager;

import java.time.Duration;

public class SendMailTest extends BaseTest{
    HomePage homePage;
    ShareByMailPage shareByMailPage;
    SearchPage searchPage;
    SearchResultPage searchResultPage;
    RegisterPage registerPage;

    String firstName = "Eslam";
    String lastName = "Fawzy";
    String mail = "eslaam@faawzy.com";

    String password = "12345678";
    String product = "macbook";

    String fullName = "Eslam Fawzy";
    String friendMail = "hashem@hashem.com";
    String msgWithMAil = "I recommend you this product";

    //First, User can register successfully
    @Test(priority = 1)
    public void testSuccessfulRegistration() throws InterruptedException{
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        Thread.sleep(2000);
        registerPage = new RegisterPage(driver);
        registerPage.register(firstName, lastName, mail, password);
        Thread.sleep(2000);
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));
    }

    //Second, User searches for the product he wants
    @Test(priority = 2, dependsOnMethods = {"testSuccessfulRegistration"})
    public void testAutoSuggest() throws InterruptedException{
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        searchPage.searchByAutoSuggest(product);
        Thread.sleep(2000);
        WindowManager.goBack();
        Thread.sleep(2000);
    }

    //Third, registered user can recommend the product to a friend by mail
    @Test(priority = 3)
    public void testSendMail(){
        shareByMailPage = new ShareByMailPage(driver);
        homePage.gotoSendMailPage();
        shareByMailPage.shareProductByMail(friendMail, mail, msgWithMAil);
    }

    //Finally, User can logout
    @Test(priority = 4)
    public void testLogout(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofMinutes(1));
        explicitWait.until(ExpectedConditions.elementToBeClickable(registerPage.logoutLink));
        registerPage.logout();
        Assert.assertTrue(homePage.loginLink.isDisplayed());
        Assert.assertTrue(homePage.loginLink.getText().contains("Log in"));
    }
}
