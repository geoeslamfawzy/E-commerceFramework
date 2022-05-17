package utilities;

import org.openqa.selenium.WebDriver;

public class  WindowManager {
    protected WebDriver driver;
    private static WebDriver.Navigation navigate;
    public WindowManager(WebDriver driver){
        this.driver = driver;
    }

    public static void goBack(){
        navigate.back();
    }

    public static void goForward(){
        navigate.forward();
    }

    public static void refreshPage(){
        navigate.refresh();
    }

    public static void navigateTo(String url){
        navigate.to(url);
    }
}
