import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NonPOMFile {
	
	private File nonPOM;   
	
	public String getLicense(String fileName, ArrayList<String> licensesFullNames, ArrayList<String> licensesAcronyms) throws IOException{
		nonPOM = new File(fileName);
		String spreadsheetURLContent = spreadsheetURLContentToString();
		String fullName = returnLicenseIfFullName(spreadsheetURLContent, licensesFullNames);
		String acr = returnLicenseIfAcronym(spreadsheetURLContent, licensesAcronyms);
		return (fullName + " " + acr);
	}
		
	public String spreadsheetURLContentToString() throws FileNotFoundException { //Puts the spreadsheet URL contents into a String.
		Scanner exampleFile = new Scanner(nonPOM); //Prepares to scan URL content.
		String result = ""; //Prepares the String the URL content will go into.
		
		while(exampleFile.hasNextLine()){ //Puts the URL content into the String.
			result += exampleFile.nextLine() + "\n";
		}
		exampleFile.close();
		return result; //Returns the URL content into String "result".
	}
	
 	public String returnLicenseIfFullName(String a, ArrayList<String> b) throws FileNotFoundException{ //If the spreadsheet URL content has any licenses (full name), it will print the license.
 		String licenseName = "";
		for(int i = 0; i < b.size(); i++){ //Compares the spreadsheet URL content against the ArrayList of licenses (full names).
			if(a.contains(b.get(i))){
				licenseName += (b.get(i) + ", ");
			}
		}
		return licenseName;
	}
 
 	
 	public String returnLicenseIfAcronym(String a, ArrayList<String> b) { //If the spreadsheet URL content has any licenses (acronym), it will print the license.
    	String licenseAcr = "";
    	for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList of licenses against the contents of the spreadsheet URL content.
			if(a.contains(b.get(i))){     
				licenseAcr += (b.get(i) + ", ");
			}
		}
		return licenseAcr;
    }
} 