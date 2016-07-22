import org.junit.Test;

import java.io.File;

//to run through a csv sheet and update the licenses by comparing url 
//and file contents as well as user input when the system can not self-determine the updated license
public class UpdateLicense { 
	private final String csvFilename = "/Users/margaretknoblock/Desktop/integrationTest.csv"; //csv file to read in
	FileCompTest fileTester = new FileCompTest();
	FileRead fileReader = new FileRead();
	FileWrite fileWriter = new FileWrite();
	WGet fileGetter = new WGet();
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
		for(currentRow = 1; currentRow<fileReader.numRows() - 2; currentRow++){
			if (fileReader.accessFile(currentRow+1, updatedLicenseCol).isEmpty()){ //checks to see if second url already has a license
				if (fileReader.accessFile(currentRow,urlCol).equals(fileReader.accessFile(currentRow+1, urlCol))){ //if urls for first and second row are the same
					addRowNewLice(currentRow+1); //add the row with the new license
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
						addRowNewLice(currentRow+1); //if same license detailed adds row with the new license
					}
					else{ //need to open files to compare
						System.out.println((currentRow+1) + " " + (currentRow+2));
						System.out.println("Not same url or same detailed");
						//System.out.println(fileReader.accessFile(currentRow, licenseDetailedCol));
						//System.out.println(currentRow);
						addRow(currentRow+1); //delete after actually adding in wget
						
						
						//**UNCOMMENT SECTION BELOW
						String file1Name = getFile(currentRow); //creates file and saves full path name as a string
						String file2Name = getFile(currentRow + 1);
						File fileOne = new File(file1Name);
						File fileTwo = new File(file2Name);
						
						/*if (fileTester.shouldGetChangesBetweenFiles(fileOne, fileTwo))
							addRowNewLice(currentRow+1);
						
						else
							addRow(currentRow+1);*/
						
						
						//if mik's code sets a different updated license, should change current license
					}	
				}
			}
			else{ //if second url already has a license, moves to next one
				//System.out.println((currentRow+1) + " " + (currentRow+2));
				System.out.println("Not empty");
				addRow(currentRow+1); //add the second row without changing the license
				currentLicense = fileReader.accessFile(currentRow+1, updatedLicenseCol); //changes current license to what is ther
			}
		}
		addRow(fileReader.numRows() -1); //fills in last row with the updated license
	}
	
	public void init(){ //initializes updated sheet info, sets current license and puts in title row of updatedSheetInfo
		updatedSheetInfo = new String[fileReader.numRows()][fileReader.numCols()];
		currentLicense = fileReader.accessFile(currentRow, updatedLicenseCol);
		addRow(0); //adds title row to new spreadsheet
		addRow(1); //adds first row to new spreadsheet (assuming first url already has a license)
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
	
	public void addRowNewLice(int rowNum){ //if the license changes
		System.out.println((currentRow+1) + " " + (currentRow+2));
		String [] row = fileReader.getRow(rowNum); //gets row from file 
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
	
	
	public void test(){
		for (currentRow = 1; currentRow <fileReader.numRows() - 2; currentRow++){
			System.out.println(currentRow);
		}
	}
}
