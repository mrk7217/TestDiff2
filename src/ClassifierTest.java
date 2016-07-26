import org.junit.Test;

import java.io.File;


public class ClassifierTest {
	private final String csvFilename = "/Users/mikaylaminton/Desktop/otherLicensesCSV.csv"; //csv file to read in
	FileRead fileReader = new FileRead();
	FileWrite fileWriter = new FileWrite();
	WGet fileGetter = new WGet();
	int currentRow = 1; //can't start at row zero because titles are there
	String currentLicense; 
	String [][] updatedSheetInfo; //creates empty 2d string array for all the updated sheet information
	
	//hard coded to fit the format of otherLicense.csv sheet
	final int licenseCol = 5; 
	final int licenseDetailedCol = 6;
	final int urlCol = 7;
	final int updatedLicenseCol = 9;
	
	@Test
	public void updateLicenses(){ //runs through sheet and prints license name in the updated licenses column
		fileReader.openFile(csvFilename); 
		init();
		//replace with classifier method
		
		fileReader.closeFile(); //should put at reached end once done testing
		writeLicense(); //writes to new spreadsheet
	}
	
	public void init(){ //initializes updated sheet info, sets current license and puts in title row of updatedSheetInfo
		updatedSheetInfo = new String[fileReader.numRows()][fileReader.numCols()]; //creates new sheet in array form
		currentLicense = fileReader.accessFile(currentRow, updatedLicenseCol);
		addRow(0); //adds title row to new spreadsheet
		addRow(1); //adds first row to new spreadsheet (assuming first url already has a license)
	}
	
	public void addRow(int rowNum){ //if no license change
		String [] row = fileReader.getRow(rowNum); //gets row from file
		for(int i=0;i<updatedSheetInfo[0].length;i++) //adds all elements of row to updated sheet info
			updatedSheetInfo[rowNum][i] = checkCommas(row[i]); //makes sure no commas are in there
	}
	
	//removes any commas from a string (commas screw up the array format when writing into a spreadsheet)
	public String checkCommas(String info){ 
		String newInfo = "";
		for(int i =0; i < info.length(); i++){
			if (info.charAt(i) != ',')
				newInfo += info.charAt(i);
		}
		return newInfo;
	}
	
	public void writeLicense(){ //writes everything from 2d array updateSheetInfo into a new csv file
		fileWriter.writeSpreadsheet(updatedSheetInfo, csvFilename);	
		System.out.println("writing sheet");
	}
}
	
	