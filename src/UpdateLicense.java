import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateLicense { 
	private final String csvFilename = "/Users/margaretknoblock/Desktop/moreTest.csv"; //csv file to read in
	POMFile pom = new POMFile();
	NonPOMFile nonPOM = new NonPOMFile();
	FileRead fileReader = new FileRead();
	FileWrite fileWriter = new FileWrite();
	WGet fileGetter = new WGet();
	int currentRow = 1; //cant start at row zero because titles are there
	String currentLicense; 
	String [][] updatedSheetInfo; //creates empty 2d string array for all the updated sheet information
	
	ArrayList<String> licensesFullNames; //initializes arraylist to hold all license names
	ArrayList<String> licensesAcronyms; //initializes arraylist to hold all license acronyms
	int countEmpty = 0;
	
	//hard coded to fit the format of otherLicense.csv sheet
	final int licenseCol = 5; 
	final int licenseDetailedCol = 6;
	final int urlCol = 7;
	final int updatedLicenseCol = 9;
	
	@Test
	public void updateLicenses(){ //runs through sheet and updated licenses in the updated licenses column
		fileReader.openFile(csvFilename); 
		init();
		findLicense();
		fileReader.closeFile(); //should put at reached end once done testing
		writeLicense(); //writes to new spreadsheet
	}
	
	public void findLicense(){ //checks to see if the two rows have the same license
		String fileName;
		String license = new String();
		for(currentRow = 1; currentRow<fileReader.numRows(); currentRow++){
			fileName = getFile(currentRow); //creates file and saves full path name as a string
			String urlName = fileReader.accessFile(currentRow, urlCol);
			if (urlName.substring(urlName.lastIndexOf('.') + 1).equals("pom")){ //check to see if pom
				try {
					license = pom.getLicense(fileName, licensesFullNames, licensesAcronyms) ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					license = nonPOM.getLicense(fileName, licensesFullNames, licensesAcronyms);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (license.equals(" "))
				countEmpty+=1;
			addRowNewLice(license);
		}
		System.out.println("There are " + countEmpty + " empty rows.");
	}
	
	
	public void init(){ //initializes updated sheet info, sets current license and puts in title row of updatedSheetInfo
		updatedSheetInfo = new String[fileReader.numRows()][fileReader.numCols()];
		currentLicense = fileReader.accessFile(currentRow, updatedLicenseCol);
		addRow(0); //adds title row to new spreadsheet
		initLicenseNames();
	}
	
	public void initLicenseNames(){
		try {
			licensesFullNames = prepareLicenseFullNames();
			licensesAcronyms = prepareLicenseAcronyms();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> prepareLicenseFullNames() throws FileNotFoundException{
		File licensesFullName = new File ("/Users/margaretknoblock/Documents/workspace/TestDiff2/licenseNames/approvedLicensesFullName.txt"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesFullName); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses with full names into the ArrayList. Each index is its own license.
			licensesInArrayList.add(forLicenses.nextLine());	
		}
		forLicenses.close();
		
		return licensesInArrayList;
	}
	
	public ArrayList<String> prepareLicenseAcronyms() throws FileNotFoundException{
    	File licensesAcronym = new File ("/Users/margaretknoblock/Documents/workspace/TestDiff2/licenseNames/approvedLicensesAcronym.txt"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesAcronym); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses (acronym) into the ArrayList. Each index is its own license. Their is a space before and after the acronyms to ensure only true licenses (and not random words) are found.
			licensesInArrayList.add(" " + forLicenses.nextLine() + " ");	
		}
		forLicenses.close();
		
		return licensesInArrayList;   	
    }
    
	public String getFile(int row){ //creates file from url and returns the path of the file as a string
		File newFile = fileGetter.getFile(fileReader.accessFile(row, urlCol));
		String filePath = fileGetter.getFilePath(newFile);
		return filePath;
	}
	
	public void addRow(int rowNum){ //if no license change
		String [] row = fileReader.getRow(rowNum); //gets row from file
		for(int i=0;i<updatedSheetInfo[0].length;i++) //adds all elements of row to updated sheet info
			updatedSheetInfo[rowNum][i] = checkCommas(row[i]); //makes sure no commas are in there
	}
	
	public void addRowNewLice(String updatedLicense){ //if the license changes
		String [] row = fileReader.getRow(currentRow); //gets row from file 
		for(int i=0;i<updatedSheetInfo[0].length;i++) //adds all elements of row to updated sheet info
			updatedSheetInfo[currentRow][i] = checkCommas(row[i]);
		updatedSheetInfo[currentRow][updatedLicenseCol] = updatedLicense; //changes license column to the updated license
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
