package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataReader {
    public String firstName, lastName, mail, password;

    public void readJson(String dataFileName) throws IOException, ParseException {
        //1- Get the path of your json file
        String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\"+dataFileName;

        //2- make an object of type file to save the data in
        File srcFile = new File(filePath);

        //3-  make an object to parse all json file
        JSONParser jsonParser = new JSONParser();
        //4- make an object contains all array in the object
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(srcFile));

        //5- Read element by element in the array
        for(Object object : jsonArray){
            JSONObject person = (JSONObject) object; //make any object in the array as json object
            firstName = (String) person.get("firstName");
            System.out.println("The first name  is "+firstName);

            lastName = (String) person.get("lastName");
            System.out.println("The last name  is "+lastName);

            mail = (String) person.get("mail");
            System.out.println("The mail is "+mail);

            password = (String) person.get("password");


        }
    }
}
