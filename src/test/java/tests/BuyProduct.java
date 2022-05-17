package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import java.util.Locale;


public class BuyProduct extends BaseTest{

    //1- Register as a user
    //2- Search for the product you want to buy, then add it into your cart
    //3- Go to Cart Page, then g

    String product = "macbook";
    int itemsInCartsBeforeAdd;
    int itemsInCartsAfterAdd;
    String firstName = "eslam";
    String lastName = "fawzy";
    String mail = "eslam@fawzy.com";
    String phone = "123456789";
    String country = "Egypt";
    String address = "downtown";
    String city = "cairo";
    String postalCode = "29647";
    String creditType = "Master card";
    String creditHolder = "Eslam";
    String creditNo = "1234567890123456789012";
    String cardCode = "1996";
    int expireMonth = 3;
    String expireYear = "2026";



    HomePage homePage;
    RegisterPage registerPage;
    SearchPage searchPage;
    SearchResultPage searchResultPage;
    AddToCartPage addToCartPage;
    CheckoutPage checkoutPage;
    OrderDetailsPage orderDetailsPage;

    @Test(priority = 1)
    public void testSuccessfulRegistration(){
        homePage = new HomePage(driver);
        homePage.gotoRegisterPage();
        registerPage = new RegisterPage(driver);
        registerPage.register("Eslam", "Fawzy", "eslam33@fawzy.com", "12345678");
        Assert.assertTrue(registerPage.confirmationMsg.getText().contains("Your registration completed"));
    }

    @Test(priority = 2,dependsOnMethods = {"testSuccessfulRegistration"})
    public void testAutoSuggest(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        searchPage.searchByAutoSuggest(product); //Search  for the item you want to add into your cart
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().toLowerCase(Locale.ROOT)
                .contains(product)); //make sure you got the right product after search
        itemsInCartsBeforeAdd = searchResultPage.getNoOfItemsInCart();
        System.out.println("The no of items before add to cart = " + itemsInCartsBeforeAdd);
    }

    @Test(priority = 3, dependsOnMethods = {"testAutoSuggest"})
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

    @Test(priority = 4, dependsOnMethods = "testAddToCart")
    public void buyProduct() throws InterruptedException{
        addToCartPage.gotoCheckoutPage();
        checkoutPage = new CheckoutPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);

        checkoutPage.fillBillingAddressInfo(firstName, lastName, mail, country, city, address, postalCode, phone);
        checkoutPage.chooseAirChippingMethod(2);

        //checkoutPage.payCash();
        Thread.sleep(2000);
        checkoutPage.payCredit(creditType, creditHolder, creditNo, expireMonth, expireYear, cardCode);

        fluentWait(checkoutPage.successfulMsg);
        Thread.sleep(2000);
        Assert.assertTrue(checkoutPage.successfulMsg.isDisplayed());
        Assert.assertEquals(checkoutPage.successfulMsg.getText(),
                "Your order has been successfully processed!");
    }

    @Test(priority = 5)
    public void testDownloadAfterBuy() throws InterruptedException{
        checkoutPage.gotoOrderDetails();
        Assert.assertTrue(orderDetailsPage.pageTitle.getText().contains("Order information"));
        orderDetailsPage.isProductVisible(product);
        orderDetailsPage.printOrder();
        Thread.sleep(3000);
        orderDetailsPage.getInvoice();
    }
}
