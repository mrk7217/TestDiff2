import org.junit.Test;

public class UpdateLicense {
  /* Psuedo Program Control
   * 
   * bool sameLicense = false
   * check license detailed? (need to know if these actually mean same licenses)
   * 
   * 
   * **need to check to see if second file has an updated license, if it does then skip down one
   * check url
   * 	if same 
   * 		sameLicense = true
   * 	else
   * 		open files (using wget)
   * 		fileread
   * 		filecomptest
   * 			if same 
   * 				sameLicense = true
   * 			if differences<certain num of characters
   * 				check with user
   * 					if user says same	
   * 						sameLicense = true 
   * //dont need else for sameLicense being false because it starts out as false
   *  \
   *  
   *  if !sameLicense 
   *  	run process again with following url
   *  else 
   *  	write license to updated license column (where will it get the license name??)
   *  
   * 
   * 
   */
	FileRead fileReader = new FileRead();
	int currentRow = 3223;
	final int licenseCol = 5;
	final int licenseDetailedCol = 6;
	final int urlCol = 7;
	final int updatedLicenseCol = 9;
	
	@Test
	public void updateLicenses(){
		checkSameLicense();
	}
	
	public void checkSameLicense(){
		fileReader.openFile();
		//if licensedetailed the same means same license add that if statement in here
		/*if (fileReader.accessFile(currentRow+1, updatedLicenseCol) == ""){ //checks to see if second url already has a license
			fileReader.accessFile(currentRow,licenseCol);
		}
		else{ //if second url already has a license, moves to next one
			nextURL();
		}*/
		
		//***was testing how i plan to check if at the end of the spreadsheet DOES NOT WORK AS OF 6/30
		
		System.out.println(fileReader.numRows());
		System.out.println(fileReader.accessFile(5, 7));
		if (reachedEnd())
			System.out.println("you have reached the end");
		else
			System.out.println("notend");

		fileReader.closeFile();//should put at reached end once done testing
	}
	
	public void writeLicense(){
		//run if licenses are the same
	}
	
	public void nextURL(){
		//runs if licenses are different or if second file has already been licensed
		if (!reachedEnd()){
			currentRow += 1;
			checkSameLicense();
		}
		else {
			System.out.println("Reached end of spreadsheet");
		}
	}
	
	public boolean reachedEnd(){
		if(currentRow==fileReader.numRows()-1) 
			return true;
		else
			return false;
	}
}
