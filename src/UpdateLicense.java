import org.junit.Test;

//bug as of 7/8 - when spreadsheet fed in with two filled updated licenses at end, reformats sheet and doesnt fill updated license column
//problem starts between line 48 and 59
//or could be something with the reached end because the two next to eachother are at the end (look at moreTest.csv for og file)


public class UpdateLicense {
	private final String csvFilename = "/Users/margaretknoblock/Desktop/moreTest.csv";
	FileRead fileReader = new FileRead();
	FileWrite fileWriter = new FileWrite();
	int currentRow = 1; //cant start at row zero because titles are there
	String currentLicense;
	final int licenseCol = 5;
	final int licenseDetailedCol = 6;
	final int urlCol = 7;
	final int updatedLicenseCol = 9;
	boolean sameLicense = false;
	String [][] updatedSheetInfo;
	
	@Test
	public void updateLicenses(){
		fileReader.openFile(csvFilename);
		init();
		checkSameLicense();
		fileReader.closeFile();//should put at reached end once done testing
		//writeLicense();
	}
	
	public void checkSameLicense(){
		//if licensedetailed the same means same license add that if statement in here (would not be other)
		if (fileReader.accessFile(currentRow+1, updatedLicenseCol).isEmpty()){ //checks to see if second url already has a license
			if (fileReader.accessFile(currentRow,urlCol) == fileReader.accessFile(currentRow+1, urlCol)){
				addRowNewLice(fileReader.getRow(currentRow+1));
				nextURL();
			}
			else{
				String urlName1 = fileReader.accessFile(currentRow, urlCol);
				String urlName2 = fileReader.accessFile(currentRow+1, urlCol);
				String urlEnd1 = (urlName1.substring(urlName1.lastIndexOf('.') + 1));
				String urlEnd2 = (urlName2.substring(urlName2.lastIndexOf('.') + 1));
				String ul1 = fileReader.accessFile(currentRow, licenseDetailedCol);
				String ul2 = fileReader.accessFile(currentRow+1, licenseDetailedCol);
				//checks to see if they are both pom files and have the same license detailed column (that isn't other)
				if (((urlEnd1.equals("pom")) && (urlEnd2.equals("pom"))) && (!fileReader.accessFile(currentRow, licenseDetailedCol).equals("Other")) && (ul1.equals(ul2))){
					addRowNewLice(fileReader.getRow(currentRow+1));
					nextURL();
				}
				else{ //problemos here
					System.out.println("Not same url or same detailed");
					addRow(fileReader.getRow(currentRow+1), currentRow+1); //delete after actually adding in wget
					//need to do wget and file comparison
				}
			}
		}
		else{ //if second url already has a license, moves to next one
			addRow(fileReader.getRow(currentRow+1), currentRow+1);
			currentLicense = fileReader.accessFile(currentRow+1, updatedLicenseCol);
			System.out.println(currentLicense);
			nextURL();
		}
	}
	
	public void init(){ //initializes updated sheet info, sets current license and puts in title row of updatedSheetInfo
		updatedSheetInfo = new String[fileReader.numRows()][fileReader.numCols()];
		currentLicense = fileReader.accessFile(currentRow, updatedLicenseCol); //need to test 
		addRow(fileReader.getRow(0),0); //adds title row to new spreadsheet
		addRow(fileReader.getRow(1),1); //adds first row to new spreadsheet
	}
	
	public void addRow(String[] row, int loc){ //if no license change
		for(int i=0;i<updatedSheetInfo[0].length;i++)
			updatedSheetInfo[loc][i] = row[i];
	}
	
	public void addRowNewLice(String[] row){ //if the license changes
		for(int i=0;i<updatedSheetInfo[0].length;i++)
			updatedSheetInfo[currentRow+1][i] = row[i];
		updatedSheetInfo[currentRow+1][updatedLicenseCol] = currentLicense;
	}
	
	public void writeLicense(){
		//run if licenses are the same (at end of program??)
		fileWriter.writeSpreadsheet(updatedSheetInfo, csvFilename);	
		System.out.println("writing sheet");
	}
	
	public void nextURL(){
		//runs if licenses are different or if second file has already been licensed
		if (!reachedEnd()){
			currentRow += 1;
			checkSameLicense();
		}
		else {
			addRow(fileReader.getRow(currentRow+1), currentRow+1);
			System.out.println("Reached end of spreadsheet");
		}
	}
	
	public boolean reachedEnd(){
		if(currentRow==fileReader.numRows()-2) {//subtract two to get second to last location (starts at location 0) 
			return true;
		}
		else{
			return false;
		}
	}
}
