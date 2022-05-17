package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultPage;
import pages.WishlistPage;

import java.util.Locale;

public class WishlistTest extends BaseTest{

    SearchPage searchPage;
    SearchResultPage searchResultPage;
    WishlistPage wishlistPage;

    String product = "macbook";
    int wishlistsNoBefore;
    int wishlistsNoAfter;

    //1- search for the item you want to add to your wishlist
    @Test(priority = 1)
    public void testAutoSuggest(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        searchPage.searchByAutoSuggest(product);
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().toLowerCase(Locale.ROOT)
                .contains(product));
    }

    //2- add the item into your wishlist
    @Test(priority = 2)
    public void testAddToWishlist() throws InterruptedException{
        wishlistsNoBefore=searchResultPage.getNoOfWishlists();
        System.out.println("number of wishlists before adding your wishlist = "+wishlistsNoBefore);

        searchResultPage.addWishList();
        Assert.assertTrue(searchResultPage.confirmAddMsg.isDisplayed());
        Assert.assertTrue(searchResultPage.confirmAddMsg
                .getText().contains("The product has been added"));
        Thread.sleep(2000);
        searchResultPage.closeMsg();
        Thread.sleep(2000);

        wishlistsNoAfter= searchResultPage.getNoOfWishlists();
        System.out.println("number of wishlists after adding your wishlist = "+wishlistsNoAfter);
        Thread.sleep(2000);

        Assert.assertTrue(wishlistsNoAfter>wishlistsNoBefore);
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void testAddCheklistIntoCart(){
        wishlistPage = new WishlistPage(driver);
        searchResultPage.gotoWishListPage();
        Assert.assertTrue(wishlistPage.wishlistTitle.isDisplayed());
        Assert.assertEquals(wishlistPage.wishlistTitle.getText(),"Wishlist");
        Assert.assertTrue(wishlistPage.productInWishlist.isDisplayed());
        Assert.assertTrue(wishlistPage.productInWishlist.getText().toLowerCase(Locale.ROOT)
                .contains(product));
        wishlistPage.addWishlistIntoCart();
    }
}
