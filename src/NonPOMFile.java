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
		String content = nonPOM.readPageToString();
		nonPOM.printLicense(content);
	}
	
	
	public String readPageToString() throws FileNotFoundException { 
		Scanner exampleFile = new Scanner(nonPOMFile); //Puts the contents of the spreadsheet URL into a String.
		String result = "";
		
		while(exampleFile.hasNextLine()){ 
			result += exampleFile.nextLine() + "\n";
		}
		exampleFile.close();
		
		return result.toLowerCase();
	}
	
	
	public void printLicense(String a) throws FileNotFoundException{
		File licenses = new File ("/Users/maramuslea/git/TestDiff2/files/approvedLicensesFullName");
		ArrayList<String> licensesInArray = new ArrayList<String>();
		Scanner forLicenses = new Scanner(licenses);
		
		while(forLicenses.hasNextLine()){
			licensesInArray.add(forLicenses.nextLine().toLowerCase());	
		}
		
		for(int i = 0; i < licensesInArray.size(); i++){
			if(a.contains(licensesInArray.get(i))){
				System.out.println(licensesInArray.get(i));
			}
		}
	}
} 