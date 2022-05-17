package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.Locale;

public class AddProductReviewTest extends BaseTest{
    HomePage homePage;
    RegisterPage registerPage;
    SearchPage searchPage;
    SearchResultPage searchResultPage;
    AddReviewPage addReviewPage;


    String product = "macbook";
    String reviewTitle = "macbook review";
    String reviewText = "This is a good mac";
    int rate = -1;

    int reviewsNoBefore;
    int reviewsNoAfter;


    //1- make a registration
    @Test(priority = 1)
    public void testSuccessfulRegistration() throws InterruptedException{
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        registerPage = new RegisterPage(driver);
        registerPage.register("Eslam", "Fawzy", "eslam32@gmail.com", "12345678");
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));
    }

    //Second, User searches for the product he wants
    @Test(priority = 2, dependsOnMethods = {"testSuccessfulRegistration"})
    public void testAutoSuggest(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        searchPage.searchByAutoSuggest(product);
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().toLowerCase(Locale.ROOT)
                .contains(product));
        //Calc the number of reviews before adding your review
        reviewsNoBefore = searchResultPage.getNoOfReviews();
        System.out.println("the number of reviews before adding review = "+ reviewsNoBefore);
    }

    //3- Add your review
    @Test(priority = 3, dependsOnMethods = {"testSuccessfulRegistration"})
    public void testReview(){
        addReviewPage = new AddReviewPage(driver);
        searchResultPage.gotoAddReview();
        addReviewPage.addReview(reviewTitle, reviewText, rate);

        Assert.assertTrue(addReviewPage.msgAfterReviewSubmit.isDisplayed());
        Assert.assertTrue(addReviewPage.msgAfterReviewSubmit.getText()
                .contains("Product review is successfully added"));
        addReviewPage.returnToProductPage();
        //Calc the number of reviews after adding your review
        reviewsNoAfter = searchResultPage.getNoOfReviews();
        System.out.println("the number of reviews after adding review = "+ reviewsNoAfter);

    }
}
