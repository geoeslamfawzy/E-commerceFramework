package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.SearchPage;
import pages.SearchResultPage;

import java.util.Locale;

public class AddToCartTest extends BaseTest{
    //1- Search for the item, then add it into your cart

    String product = "macbook";
    int itemsInCartsBeforeAdd;
    int itemsInCartsAfterAdd;

    SearchPage searchPage;
    SearchResultPage searchResultPage;
    AddToCartPage addToCartPage;

    @Test(priority = 1)
    public void testAutoSuggest(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        searchPage.searchByAutoSuggest(product); //Search  for the item you want to add into your cart
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().toLowerCase(Locale.ROOT)
                .contains(product)); //make sure you got the right product after search
        itemsInCartsBeforeAdd = searchResultPage.getNoOfItemsInCart();
        System.out.println("The no of items before add to cart = " + itemsInCartsBeforeAdd);
    }

    @Test(priority = 2)
    public void testAddToCart() throws InterruptedException{
        addToCartPage = new AddToCartPage(driver);
        searchResultPage.addProductToCart(); // Add the product into cart
        Thread.sleep(2000);
        //make sure the number of items have increased after adding your product into cart
        itemsInCartsAfterAdd = searchResultPage.getNoOfItemsInCart();
        System.out.println("The no of items after add to cart = " + itemsInCartsAfterAdd);
        Assert.assertTrue(itemsInCartsAfterAdd > itemsInCartsBeforeAdd);
        searchResultPage.closeMsg(); //Close the message
        searchResultPage.gotoAddToCartPage();

        //Make sure you are in the right page
        Assert.assertTrue(addToCartPage.pageTitle.isDisplayed());
        Assert.assertEquals(addToCartPage.pageTitle.getText(), "Shopping cart");
    }

    @Test(priority = 3)
    public void testCartPage(){
        addToCartPage.removeProductFromCart("mac");
        Assert.assertTrue(addToCartPage.emptyCartMsg.isDisplayed());
        Assert.assertEquals(addToCartPage.emptyCartMsg.getText(), "Your Shopping Cart is empty!");
    }
}
