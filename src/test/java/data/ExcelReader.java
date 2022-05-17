package data;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    static FileInputStream fileInputStream = null;
    public FileInputStream getFileInputStream(){
        //We get the path of the file we want to read
        String filePath = System.getProperty("user.dir")+ "\\src\\test\\java\\data\\userData.xlsx";
        //We want to get the data from excel file to store in an object of type file
        File srcFile = new File(filePath); //Read the file from the file path
        try {
            fileInputStream = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Test data is not found, please check the path of your test data\n"+e.getMessage());
            System.exit(0); //don't continue
        }
        return fileInputStream;
    }

    public Object[][] getExcelData() throws IOException {
        //to getExcelData, first, we need to execute "getFileInputStream" method and store it an object
        fileInputStream = getFileInputStream(); //execution of method is stored in an object of type FileInputStream

        //The excel file is named as workbook, so we need to read the file first
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        //2- from the file, we need to read the sheet (as the excel file contains multiple sheets)
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowsNo = sheet.getLastRowNum()+1; //No of rows in our sheet
        int colsNo = 4; //No of columns in our sheet

        //then store the data in the excel in a 2D array of the same size of the excel sheet
        String [][] excelData = new String[rowsNo][colsNo];

        //Read the data in the sheet, cell by cell in the array
        for(int i=0; i<rowsNo; i++){
            for(int j=0; j<colsNo; j++){
                XSSFRow row = sheet.getRow(i); //get the row
                excelData[i][j] = row.getCell(j).toString(); //read each cell in this row
            }
        }
        workbook.close(); //Close the excel file after you copy the data in the array
        return excelData;
    }
}
