package tests.DD;

import data.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.io.IOException;
import java.time.Duration;

public class LinkedInEndorse extends BaseTest {
    WebDriverWait wait;
    @DataProvider(name = "excelData")
    public Object[][] registerDataFromExcel() throws IOException {
        //Get the data from Excel Reader Class
        ExcelReader excelReader = new ExcelReader();
        return excelReader.getExcelData();
    }

    @Test(priority = 1, dataProvider = "excelData")
    public void linkInLogin(){
        driver.navigate().to("https://www.linkedin.com/");
        WebElement email= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("session_key")));
        WebElement password= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("session_password")));
        WebElement login= wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sign-in-form__submit-button")));

        //put your email here
        email.sendKeys("01125669737");
        //put your password here
        password.sendKeys("24233291");
        login.click();
    }


}
