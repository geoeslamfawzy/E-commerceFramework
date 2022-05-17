package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultPage;

public class AddProductToWishlistTest extends BaseTest{

    String productName = "Apple MacBook Pro 13-inch";
    SearchPage searchPage;
    SearchResultPage searchResultPage;

    //1-search for product
    @Test
    public void testSearch(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);

        searchPage.searchForProduct(productName);
        searchResultPage.clickOnProduct();
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().equalsIgnoreCase(productName));
    }


    //2-In the search result page, go to add to wishlist
    //Assert the successful add message, then close it

    //3- goto wishlist page  to check if the list contains that item
}
