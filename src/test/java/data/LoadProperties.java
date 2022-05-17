package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    //1- Load the properties file from its folder
    public static Properties userData = loadProperties(System.getProperty("user.dir")+
            "\\src\\main\\java\\Properties\\userData.properties");

    private static Properties loadProperties(String path){
        Properties properties = new Properties();

        //Stream for reading the file
        try {
            FileInputStream fileStream = new FileInputStream(path); //This to make a file contains the data
            properties.load(fileStream); // This to load the file
        } catch (FileNotFoundException e) {
            System.out.println("You have an error in reading file \n"+e.getMessage());
        } catch (IOException e) {
            System.out.println("You have an error in loading file \n"+e.getMessage());
        }
        /**
         * The method will return by the properties, which load the stream(fileStream) occured throughout reading
         * the file that is in the path(path) i will give to the method
         */
        return properties;
    }
}
