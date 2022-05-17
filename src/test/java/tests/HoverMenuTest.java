package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HoverMenuTest extends BaseTest{
    HomePage homePage;

    @Test
    public void testUserCanHover(){
        homePage = new HomePage(driver);
        homePage.selectNoteBooksMenue();
        Assert.assertTrue(driver.getCurrentUrl().contains("notebooks"));
    }
}

