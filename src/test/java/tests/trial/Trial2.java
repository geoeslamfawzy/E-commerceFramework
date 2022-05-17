package tests.trial;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import pages.SearchResultPage;
import tests.BaseTest;

public class Trial2 extends BaseTest {
    HomePage homePage;
    String product = "macbook";
    SearchPage searchPage;
    SearchResultPage searchResultPage;
    //First, we search for any product
    @Test(priority = 1)
    public void testAutoSuggest() throws Exception{
        homePage = new HomePage(driver);
        homePage.gotoContactUsPage();
        getWindowManager().refreshPage();
        getWindowManager().goForward();
        getWindowManager().navigateTo("https://www.google.com/");

    }

}
