import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class FileWrite { //writes to a new spreadsheet
	String sheetName;
	String sheetExt = "UpdatedLicenses.csv"; //hardcoded extension added to an old spreadsheet name
	String sheetLoc = "sheets/";  //location of new spreadsheet
	
	public void writeSpreadsheet(String [][] entries, String oldSheetName){ //takes in 2d array of sheet with updated licenses and old sheet name
		try{
			oldSheetName = oldSheetName.substring(oldSheetName.lastIndexOf("/")); //gets name of old spreadsheet
			if(oldSheetName.contains(".")) //gets rid of any file extension if exists
				sheetName = oldSheetName.substring(0, oldSheetName.indexOf(".")); 
			else
				sheetName = oldSheetName;
			String sheet = sheetLoc + sheetName + sheetExt; //concatenates string name with its new extension and location to create the new spreadsheet
			CSVWriter writer = new CSVWriter(new FileWriter(sheet), '\t');
			for(int i = 0; i < entries.length; i ++){
				writer.writeNext(entries[i],false);  //writes rows of entries into the new csv file
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
