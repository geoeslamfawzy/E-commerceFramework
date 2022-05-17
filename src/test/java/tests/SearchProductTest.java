package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultPage;

public class SearchProductTest extends BaseTest{
    String productName = "Apple MacBook Pro 13-inch";
    SearchPage searchPage;
    SearchResultPage searchResultPage;

    @Test
    public void testSearch(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);

        searchPage.searchForProduct(productName);
        searchResultPage.clickOnProduct();
       // Assert.assertEquals(searchResultPage.productTitleAfterSearch.getText(), productName);
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().equalsIgnoreCase(productName));
    }

}
