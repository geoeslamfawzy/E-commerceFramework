package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import pages.SearchResultPage;

import java.util.Locale;

public class ChangeCurrencyTest extends BaseTest {
    HomePage homePage;
    String product = "macbook";
    SearchPage searchPage;
    SearchResultPage searchResultPage;

    //First, we search for any product
    @Test(priority = 1)
    public void testAutoSuggest(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        searchPage.searchByAutoSuggest(product);
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().toLowerCase(Locale.ROOT)
                .contains(product));
    }

    //second, We change the currency
    @Test(priority = 2)
    public void changeCurrency() {
        homePage = new HomePage(driver);

        //make sure that the price was in dollars before changing currency
        System.out.println("The price before changing the currency = "
                +searchResultPage.productPriceLabel.getText());
        Assert.assertTrue(searchResultPage.productPriceLabel.getText().contains("$"));

        homePage.changeCurrency("Euro"); //Change currency to Euro

        //Make sure that the price has already changed
        System.out.println("The price before changing the currency = "
                +searchResultPage.productPriceLabel.getText());
        Assert.assertTrue(searchResultPage.productPriceLabel.getText().contains("€"));

    }
}
