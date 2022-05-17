package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver){super(driver);}

    @FindBy(name = "save")
    WebElement billingAddressBtn;

    @FindBy(css = "button.button-1.shipping-method-next-step-button")
    WebElement shippingBtn;

    @FindBy(css = "button.button-1.payment-method-next-step-button")
    WebElement paymentBtn;

    @FindBy(xpath = "//button[@class='button-1 payment-info-next-step-button']")
    WebElement paymentInfoBtn;

    @FindBy(xpath = "//button[@class='button-1 confirm-order-next-step-button']")
    WebElement confirmBtn;

    @FindBy(xpath = "//button[@class='button-1 order-completed-continue-button']")
    WebElement continueToHome;



    @FindBy(id = "BillingNewAddress_FirstName")
    WebElement firstName;

    @FindBy(id = "BillingNewAddress_LastName")
    WebElement lastName;

    @FindBy(id = "BillingNewAddress_Email")
    WebElement mail;

    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement countryDropDownMenue;

    @FindBy(id = "BillingNewAddress_City")
    WebElement city;

    @FindBy(id = "BillingNewAddress_Address1")
    WebElement address;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement postalCode;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement phoneNo;

    @FindBy(id = "shippingoption_0")
    WebElement shippingByLand;

    @FindBy(id = "shippingoption_1")
    WebElement shippingByAir1;

    @FindBy(id = "shippingoption_2")
    WebElement shippingByAir2;

    @FindBy(id = "paymentmethod_0")
    WebElement cashPay;

    @FindBy(id ="paymentmethod_1")
    WebElement creditPay;

    @FindBy(id="CreditCardType")
    WebElement creditCardType;

    @FindBy(id = "CardholderName")
    WebElement cardholderName;

    @FindBy(id = "CardNumber")
    WebElement cardNumber;

    @FindBy(id = "ExpireMonth")
    WebElement expireMonth;

    @FindBy(id = "ExpireYear")
    WebElement expireYear;

    @FindBy(id = "CardCode")
    WebElement cardCode;


    @FindBy(tagName = "strong")
    public WebElement successfulMsg;

    @FindBy(css = "div.details-link>a")
    public WebElement orderDetails;

    public void fillBillingAddressInfo(String firstName, String lastName, String mail, String country,
                                       String city, String address, String postalCode, String phoneNo){
        writeText(this.firstName, firstName);
        writeText(this.lastName, lastName);
        writeText(this.mail, mail);
        select = new Select(this.countryDropDownMenue);
        select.selectByVisibleText(country);
        writeText(this.city, city);
        writeText(this.address, address);
        writeText(this.postalCode, postalCode);
        writeText(this.phoneNo, phoneNo);
        clickOn(billingAddressBtn);
    }
    public void fillBillingAddressInfo(String country, String city, String address,
                                       String postalCode, String phoneNo){
        select = new Select(this.countryDropDownMenue);
        select.selectByVisibleText(country);
        writeText(this.city, city);
        writeText(this.address, address);
        writeText(this.postalCode, postalCode);
        writeText(this.phoneNo, phoneNo);
        clickOn(billingAddressBtn);
    }

    public void chooseLandShipping(){
        clickOn(shippingByLand);
        clickOn(shippingBtn);
    }

    public void chooseAirChippingMethod(int days){
         if(days>1){
            clickOn(shippingByAir2);
        }
         else{
             clickOn(shippingByAir1);
         }
         clickOn(shippingBtn);
    }

    public void payCash(){
        clickOn(cashPay);
        clickOn(paymentBtn);
        clickOn(paymentInfoBtn);
        clickOn(confirmBtn);
    }
    public void payCredit(String creditCardType, String cardholderName,String cardNumber,
                          int expireMonth, String expireYear, String cardCode){
        clickOn(creditPay);
        clickOn(paymentBtn);

        //Select the credit type, visa, mastercard, ... etc
        select = new Select(this.creditCardType);
        select.selectByVisibleText(creditCardType);

        //Complete the payment info
        writeText(this.cardholderName, cardholderName);
        writeText(this.cardNumber, cardNumber);

        //Select the credit expiration month
        select = new Select(this.expireMonth);
        select.selectByIndex(expireMonth-1);

        //Select the credit expiration year
        select = new Select(this.expireYear);
        select.selectByVisibleText(expireYear);

        //write the secret credit code
        writeText(this.cardCode, cardCode);

        clickOn(paymentInfoBtn);
        clickOn(confirmBtn);
    }

    public void returnToHome(){
        clickOn(continueToHome);
    }

    public void gotoOrderDetails(){
        clickOn(orderDetails);
    }

}
