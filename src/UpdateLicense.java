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
	int currentRow = 1; //cant start at row zero because titles are there
	final int licenseCol = 5;
	final int licenseDetailedCol = 6;
	final int urlCol = 7;
	final int updatedLicenseCol = 9;
	boolean sameLicense = false;
	
	@Test
	public void updateLicenses(){
		fileReader.openFile();
		checkSameLicense();
		fileReader.closeFile();//should put at reached end once done testing
	}
	
	public void checkSameLicense(){
		//if licensedetailed the same means same license add that if statement in here (would not be other)
		if (fileReader.accessFile(currentRow+1, updatedLicenseCol).isEmpty()){ //checks to see if second url already has a license
			if (fileReader.accessFile(currentRow,urlCol) == fileReader.accessFile(currentRow+1, urlCol)){
				System.out.println("Same Url");
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
					System.out.println("Same license detailed (both pom)");
				}
				else{
					System.out.println("Not same url or same detailed");
					//need to do wget and file comparison
				}
			}
		}
		else{ //if second url already has a license, moves to next one
			System.out.println("not empty");
			System.out.println(fileReader.accessFile(currentRow+1, updatedLicenseCol));
			//nextURL();
		}
		
		if (sameLicense){
			//write in updated license
		}
		else{
			//write in stars or somethin else
		}
		
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
		if(currentRow==fileReader.numRows()-2) //subtract two to get second to last location (starts at location 0) 
			return true;
		else
			return false;
	}
}
