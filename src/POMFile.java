import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class POMFile {
		
	private File pomFile;   
	ArrayList<String> licensesFullNameInArrayList;
	ArrayList<String> licensesAcronymInArrayList;
	
	public POMFile(){
	}
	
	
	
	public String getLicense(String fileName) throws IOException{
		pomFile = new File(fileName);
		String pomContent;
		pomContent = readFirstPOMPageToString();
		
		String goodURL = findURL(pomContent);
		String contentOfWebpage = webpage(goodURL);
		String fullName = returnLicenseIfFullName(contentOfWebpage, licensesFullNameInArrayList);
		
		String acr = returnLicenseIfAcronym(contentOfWebpage, licensesAcronymInArrayList);
		return(fullName + " " + acr);
	}
	
	public void init(){
		try {
			licensesFullNameInArrayList = prepareLicensesIfFullName();
			licensesAcronymInArrayList = prepareLicensesIfAcronym();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readFirstPOMPageToString() throws FileNotFoundException { //The entire POM file is moved into a String that retains the file's structure.
		Scanner examplePOMFile = new Scanner(pomFile);                      
		String result = "";
		
		while(examplePOMFile.hasNextLine()){ 
			result += examplePOMFile.nextLine() + "\n";
		}
		examplePOMFile.close();
		
		return result;
	}
		
	public String findURL(String a){ //The URL is found within the POM file using reliable POM file markers.
		int b = a.indexOf("<license>");
		a = a.substring(b);
		
		int c = a.indexOf("<url>");
		a = a.substring(c + 5);
		
		int d;
		if((d = a.indexOf("</url>")) >=0){
			a = a.substring(0, d);
		}else {
			a = "";
		}

		return a;
	}
	
	public String webpage(String a) throws IOException{	//Opens the URL from the POM file, then reads and saves the contents of the entire webpage.
		URL theURL = new URL(a);                        
        BufferedReader in = new BufferedReader(new InputStreamReader(theURL.openStream()));

        String inputLine; 
        String webpageWithLicense = "";
        while ((inputLine = in.readLine()) != null) {
            webpageWithLicense += inputLine;
        }
        in.close();

        return webpageWithLicense;
	}
	
	public ArrayList<String> prepareLicensesIfFullName() throws FileNotFoundException{
		File licensesFullName = new File ("/Users/margaretknoblock/Documents/workspace/TestDiff2/licenseNames/approvedLicensesFullName.txt"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesFullName); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses with full names into the ArrayList. Each index is its own license.
			licensesInArrayList.add(forLicenses.nextLine());	
		}
		forLicenses.close();
		
		return licensesInArrayList;
	}
	
    public String returnLicenseIfFullName(String a, ArrayList<String> b) throws FileNotFoundException{
    	String licenseName = "";
		for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(b.get(i))){     
				licenseName += (b.get(i) + ", ");
			}
		}
		return licenseName;
	}
    
    public ArrayList<String> prepareLicensesIfAcronym() throws FileNotFoundException{
    	File licensesAcronym = new File ("/Users/margaretknoblock/Documents/workspace/TestDiff2/licenseNames/approvedLicensesAcronym.txt"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesAcronym); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses with full names into the ArrayList. Each index is its own license.
			licensesInArrayList.add(" " + forLicenses.nextLine() + " ");	
		}
		forLicenses.close();
		
		return licensesInArrayList;
    	
    }
    
    public String returnLicenseIfAcronym(String a, ArrayList<String> b) {
    	String licenseAcr = "";
    	for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(b.get(i))){
				licenseAcr += (b.get(i) + ", ");
			}
		}
    	return licenseAcr;
    }
}
