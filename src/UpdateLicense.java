import org.junit.Test;

//to run through a csv sheet and update the licenses by comparing url 
//and file contents as well as user input when the system can not self-determine the updated license
public class UpdateLicense { 
	private final String csvFilename = "/Users/margaretknoblock/Desktop/moreTest.csv"; //csv file to read in
	FileRead fileReader = new FileRead();
	FileWrite fileWriter = new FileWrite();
	int currentRow = 1; //cant start at row zero because titles are there
	String currentLicense; 
	String [][] updatedSheetInfo; //creates empty 2d string array for all the updated sheet information
	
	//hard coded to fit the format of otherLicense.csv sheet
	final int licenseCol = 5; 
	final int licenseDetailedCol = 6;
	final int urlCol = 7;
	final int updatedLicenseCol = 9;
	
	@Test
	public void updateLicenses(){ //runs through sheet and updated licenses in the updated licenses column
		fileReader.openFile(csvFilename); 
		init();
		checkSameLicense();
		fileReader.closeFile(); //should put at reached end once done testing
		writeLicense(); //writes to new spreadsheet
	}
	
	public void checkSameLicense(){ //checks to see if the two rows have the same license
		if (fileReader.accessFile(currentRow+1, updatedLicenseCol).isEmpty()){ //checks to see if second url already has a license
			if (fileReader.accessFile(currentRow,urlCol) == fileReader.accessFile(currentRow+1, urlCol)){ //if urls for first and second row are the same
				addRowNewLice(fileReader.getRow(currentRow+1)); //add the row with the new license
				nextURL(); //move to next row
			}
			else{ // urls not same so checks license detailed
				String urlName1 = fileReader.accessFile(currentRow, urlCol);
				String urlName2 = fileReader.accessFile(currentRow+1, urlCol);
				String urlEnd1 = (urlName1.substring(urlName1.lastIndexOf('.') + 1)); 
				String urlEnd2 = (urlName2.substring(urlName2.lastIndexOf('.') + 1));
				String ul1 = fileReader.accessFile(currentRow, licenseDetailedCol);
				String ul2 = fileReader.accessFile(currentRow+1, licenseDetailedCol);
				//checks to see if they are both pom files and have the same license detailed column (that isn't other)
				if (((urlEnd1.equals("pom")) && (urlEnd2.equals("pom"))) && (!fileReader.accessFile(currentRow, licenseDetailedCol).equals("Other")) && (ul1.equals(ul2))){
					addRowNewLice(fileReader.getRow(currentRow+1)); //if same license detailed adds row with the new license
					nextURL(); //move to next row 
				}
				else{ //need to open files to compare
					System.out.println("Not same url or same detailed");
					addRow(fileReader.getRow(currentRow+1), currentRow+1); //delete after actually adding in wget
					//need to do wget and file comparison
					nextURL();
				}
			}
		}
		else{ //if second url already has a license, moves to next one
			addRow(fileReader.getRow(currentRow+1), currentRow+1); //add the second row without changing the license
			currentLicense = fileReader.accessFile(currentRow+1, updatedLicenseCol); //changes current license to what is there
			nextURL(); //moves to next row
		}
	}
	
	public void init(){ //initializes updated sheet info, sets current license and puts in title row of updatedSheetInfo
		updatedSheetInfo = new String[fileReader.numRows()][fileReader.numCols()];
		currentLicense = fileReader.accessFile(currentRow, updatedLicenseCol);
		addRow(fileReader.getRow(0),0); //adds title row to new spreadsheet
		addRow(fileReader.getRow(1),1); //adds first row to new spreadsheet (assuming first url already has a license)
	}
	
	public void addRow(String[] row, int loc){ //if no license change
		for(int i=0;i<updatedSheetInfo[0].length;i++) //adds all elements of row to updated sheet info
			updatedSheetInfo[loc][i] = checkCommas(row[i]); //makes sure no commas are in there
	}
	
	public void addRowNewLice(String[] row){ //if the license changes
		for(int i=0;i<updatedSheetInfo[0].length;i++) //adds all elements of row to updated sheet info
			updatedSheetInfo[currentRow+1][i] = checkCommas(row[i]);
		updatedSheetInfo[currentRow+1][updatedLicenseCol] = currentLicense; //changes license column to the updated license
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
	
	public void nextURL(){ //movees to next row
		//runs if licenses are different or if second file has already been licensed
		if (!reachedEnd()){ //ensures that not at end of spreadsheet
			currentRow += 1;
			checkSameLicense(); //checks licenses
		}
		else { //if at end, adds last row
			addRow(fileReader.getRow(currentRow+1), currentRow+1); //assuming last row will have an updated license
			System.out.println("Reached end of spreadsheet");
		}
	}
	
	public boolean reachedEnd(){ //checks if at end of spreadsheet
		if(currentRow==fileReader.numRows()-2) //subtract two to get second to last location (starts at location 0) 
			return true;
		else
			return false;
	}
}
