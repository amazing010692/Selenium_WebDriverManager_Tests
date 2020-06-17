package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDemo {

	//public static void main(String[] args) throws IOException {
	public ArrayList<String> getdata(String testCaseName) throws IOException {
		
		//First create an ArrayList of type String
		ArrayList<String> a = new ArrayList<String>();
		
		//Create FileInputStream object pointing towards the excel (make sure that the filepath is within double quotes
		FileInputStream fis = new FileInputStream("C:\\Users\\hello\\Documents\\TestDataExcel.xlsx");
		
		//Create an object of XSSFWorkbook class to take control of the entire excel application.
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);	
		
		//Below logic to fetch “TestData” sheet from "TestDataExcel" file.
		int sheets = workbook.getNumberOfSheets();						//Iterate through each sheet
		for(int i = 0; i < sheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("TestData")) {
				XSSFSheet sheet = workbook.getSheetAt(i);				//Grab "TestData" sheet
				Iterator<Row> rows = sheet.iterator();					//Iterate through each rows
				Row firstrow = rows.next();								//We are on the first row now.
				Iterator<Cell> ce = firstrow.cellIterator();			//Iterate through each cell of the first row.
				/*Next, we will define 2 variables: ‘k’ and ‘column’. The ‘column’ variable will 
				 store the column index (starts from 0). The variable ‘k’ will keep getting incremented with each ‘while’ loop. */	 
				int k = 0;
				int column = 0;
				/*Create a ‘while’ loop that will loop till the time a cell is present. 
				If the value of first cell is equal to “Testcases”, we will grab the column*/
				while(ce.hasNext()) {			//Looping until the time cell is present.
					Cell value = ce.next();		//In 1st loop, we are in the 1st cell of 1st row.
												//In 2nd loop (if any) we will move to the 2nd cell of 1st row.
					if(value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						//grab desired column
						column = k;
					}
					k++;
				}
				//Print the value of ‘column’ variable
				System.out.println(column);
				
				/*Next we will scan all the rows of ‘Testcases’ column and find where ‘TC3’ test case row is. 
				Once we have access to desired row, we will get access to all cells of the ‘TC3’ test case row*/
				while(rows.hasNext()) {
					Row r = rows.next();
					//if(r.getCell(column).getStringCellValue().equalsIgnoreCase("TC3")) {
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						//After grabbing "TC3" test case row, we will get the cell contents of that row.
						Iterator<Cell> cv = r.cellIterator(); //Iterate through each cell of TC3 row.
						while(cv.hasNext()) {
							//System.out.println(cv.next().getStringCellValue());
							a.add(cv.next().getStringCellValue());
						}
					}
				}	
			}
		}
		//Return the array after the first ‘for’ loop is over,
		return a;
				
	}

}
