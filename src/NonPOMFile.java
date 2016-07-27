import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NonPOMFile {
	
	private File nonPOMFile;   
	
	public NonPOMFile(File nonPOMFile){
		this.nonPOMFile = nonPOMFile;
	}
	
	public static void main(String[] args) throws IOException{
		NonPOMFile nonPOM = new NonPOMFile(new File("/Users/maramuslea/Documents/examplePOMFile.txt"));
		String spreadsheetURLContent = nonPOM.spreadsheetURLContentToString();
		ArrayList<String> licensesWithFullNameInArrayList = nonPOM.prepareLicensesIfFullName();
		nonPOM.printLicenseIfFullName(spreadsheetURLContent, licensesWithFullNameInArrayList);
		ArrayList<String> licensesAcronymInArrayList = nonPOM.prepareLicensesIfAcronym();
		nonPOM.printLicenseIfAcronym(spreadsheetURLContent, licensesAcronymInArrayList);
	}
		
	public String spreadsheetURLContentToString() throws FileNotFoundException { //Puts the spreadsheet URL contents into a String.
		Scanner exampleFile = new Scanner(nonPOMFile); //Prepares to scan URL content.
		String result = ""; //Prepares the String the URL content will go into.
		
		while(exampleFile.hasNextLine()){ //Puts the URL content into the String.
			result += exampleFile.nextLine() + "\n";
		}
		exampleFile.close();

		return result; //Returns the URL content into String "result".
	}
	
	public ArrayList<String> prepareLicensesIfFullName() throws FileNotFoundException{ //Puts the file with the licenses (full name) into an ArrayList.
		File licensesFullName = new File ("/Users/maramuslea/git/TestDiff2/files/approvedLicensesFullName"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesFullName); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses with full names into the ArrayList. Each index is its own license.
			licensesInArrayList.add(forLicenses.nextLine());	
		}
		forLicenses.close();
		
		return licensesInArrayList;
	}
	
 	public void printLicenseIfFullName(String a, ArrayList<String> b) throws FileNotFoundException{ //If the spreadsheet URL content has any licenses (full name), it will print the license.
	
		for(int i = 0; i < b.size(); i++){ //Compares the spreadsheet URL content against the ArrayList of licenses (full names).
			if(a.contains(b.get(i))){
				System.out.println(b.get(i));
			}
		}
	}
 	
 	
 	public ArrayList<String> prepareLicensesIfAcronym() throws FileNotFoundException{ //Puts the file with the licenses (acronym) into an ArrayList.
    	File licensesAcronym = new File ("/Users/maramuslea/Documents/approvedLicensesAcronym.txt"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesAcronym); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses with full names into the ArrayList. Each index is its own license.
			licensesInArrayList.add(" " + forLicenses.nextLine() + " ");	
		}
		forLicenses.close();
		
		return licensesInArrayList;
    	
    }
 	
 	public void printLicenseIfAcronym(String a, ArrayList<String> b) { //If the spreadsheet URL content has any licenses (acronym), it will print the license.
    	
    	for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList of licenses against the contents of the spreadsheet URL content.
			if(a.contains(b.get(i))){     
				System.out.println(b.get(i));
			}
		}
    }
} 