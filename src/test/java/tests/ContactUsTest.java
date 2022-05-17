package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends BaseTest{
    String fullname = "Eslam Fawwzy";
    String mail = "eslaam@fawzy.com";
    String enquiry = "I have Enquiry\n Please solve the problem";
    HomePage homePage;
    ContactUsPage contactUsPage;
    @Test
    public void testContactUs() throws InterruptedException {
        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);

        homePage.gotoContactUsPage();

        contactUsPage.makeInquiry(fullname, mail, enquiry);

        Assert.assertTrue(contactUsPage.msgAfterEnquiry.isDisplayed());
        Assert.assertTrue(contactUsPage.msgAfterEnquiry.getText()
                .contains("Your enquiry has been successfully sent to the store owner"));
    }
}
