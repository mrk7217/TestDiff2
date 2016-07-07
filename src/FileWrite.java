import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.opencsv.CSVWriter;


public class FileWrite {
	String oldSheetName = "yourfile.csv";
	String sheetName;
	String sheetExt = "UpdatedLicenses.csv";
	String sheetLoc = "sheets/";
	
	public void writeSpreadsheet(String [][] entries){ //takes in 2d array of sheet with updated licenses
		try{
			if(oldSheetName.contains("."))
				sheetName= oldSheetName.substring(0, oldSheetName.indexOf(".")); 
			else
				sheetName = oldSheetName;
			String sheet = sheetLoc + sheetName + sheetExt;
			CSVWriter writer = new CSVWriter(new FileWriter(sheet), '\t');
			for(int i = 0; i < entries.length; i ++){
				writer.writeNext(entries[i]);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

/* to do with file writing:
 * have same name as original file but updated
 * loop through array to add the data
 */
