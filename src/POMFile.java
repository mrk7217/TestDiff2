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
	
	public POMFile(File pomFile){
		this.pomFile = pomFile;
	}
	
	public static void main(String[] args) throws IOException{
		POMFile pom = new POMFile(new File("/Users/maramuslea/Documents/examplePOMFile.txt"));
		String pomContent = pom.readFirstPOMPageToString();
		String goodURL = pom.findURL(pomContent);
		String contentOfWebpage = pom.webpage(goodURL);
		ArrayList<String> licensesFullNameInArrayList = pom.prepareLicensesIfFullName();
		pom.printLicenseIfFullName(contentOfWebpage, licensesFullNameInArrayList);
		ArrayList<String> licensesAcronymInArrayList = pom.prepareLicensesIfAcronym();
		pom.printLicenseIfAcronym(contentOfWebpage, licensesAcronymInArrayList);
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
		File licensesFullName = new File ("/Users/maramuslea/git/TestDiff2/files/approvedLicensesFullName"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesFullName); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses with full names into the ArrayList. Each index is its own license.
			licensesInArrayList.add(forLicenses.nextLine());	
		}
		forLicenses.close();
		
		return licensesInArrayList;
	}
	
    public void printLicenseIfFullName(String a, ArrayList<String> b) throws FileNotFoundException{
    	
		for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(b.get(i))){     
				System.out.println(b.get(i));
			}
		}
	}
    
    public ArrayList<String> prepareLicensesIfAcronym() throws FileNotFoundException{
    	File licensesAcronym = new File ("/Users/maramuslea/Documents/approvedLicensesAcronym.txt"); //Bring the File into the code.
		ArrayList<String> licensesInArrayList = new ArrayList<String>(); //Prepare ArrayList to put the licenses with full names into it.
		Scanner forLicenses = new Scanner(licensesAcronym); //Prepare Scanner that is necessary to move the File to the ArrayList.
		
		while(forLicenses.hasNextLine()){ //Input the licenses with full names into the ArrayList. Each index is its own license.
			licensesInArrayList.add(" " + forLicenses.nextLine() + " ");	
		}
		forLicenses.close();
		
		return licensesInArrayList;
    	
    }
    
    public void printLicenseIfAcronym(String a, ArrayList<String> b) {
    	
    	for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(b.get(i))){     
				System.out.println(b.get(i));
			}
		}
    }
}
