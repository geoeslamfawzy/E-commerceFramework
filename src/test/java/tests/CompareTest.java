package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductComparisonPage;
import pages.SearchPage;
import pages.SearchResultPage;

public class CompareTest extends BaseTest{
    //1- Search for product one, then add it to compare
    //2- Search for product two
    //3- goto compare page to compare between the two products
    String product1 = "mac";
    String product2 = "asus";



    SearchPage searchPage;
    SearchResultPage searchResultPage;
    ProductComparisonPage productComparisonPage;

    @Test(priority = 1)
    public void testAddproductsForCompare(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);

        //Search for the first product,  then add it to compare
        searchPage.searchByAutoSuggest(product1);
        searchResultPage.addToCompare();
        Assert.assertTrue(searchResultPage.confirmAddMsg.isDisplayed());
        Assert.assertTrue(searchResultPage.confirmAddMsg.getText()
                .contains("The product has been added"));
        searchResultPage.closeMsg();

        //Search for the second product, then add it to compare
        searchPage.searchByAutoSuggest(product2);
        searchResultPage.addToCompare();
        Assert.assertTrue(searchResultPage.confirmAddMsg.isDisplayed());
        Assert.assertTrue(searchResultPage.confirmAddMsg.getText()
                .contains("The product has been added"));
        searchResultPage.gotoComparisonPage();
    }

    @Test(priority = 2)
    public void testCompareBetweenProducts(){
        productComparisonPage = new ProductComparisonPage(driver);
        Assert.assertTrue(productComparisonPage.pageTitle.isDisplayed());
        Assert.assertEquals(productComparisonPage.pageTitle.getText(), "Compare products");
        productComparisonPage.compareProducts();

        productComparisonPage.clearCompareList();
        Assert.assertTrue(productComparisonPage.noDataMsg.isDisplayed());
        Assert.assertEquals(productComparisonPage.noDataMsg.getText(),
                "You have no items to compare.");
    }
}
