package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {
    //Create a method to take a screenshot on failure
    public static void captureScreenshot(WebDriver driver, String screenshotName) throws IOException {

        //This to put the screenshot in a specific destination
        //Always save the screenshot in Screenshots folder
        Path dest = Paths.get("./Screenshots", screenshotName+".png");

        //Make a directory to save your screenshot in it
        //The directory will be the path you previously detected (dest)
        //Make path you previously detected (dest) as the directory where your screenshot will be saved in
        Files.createDirectories(dest.getParent());

        //Make the destination path, after converting it into a string, as a file output of type FileOutputStream
        FileOutputStream out = new FileOutputStream(dest.toString());
        out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        out.close();
    }



}
