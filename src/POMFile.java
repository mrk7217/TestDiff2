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
		ArrayList<String> contentOfWebpageInArrayList = pom.putWebpageInArrayList(contentOfWebpage);
		pom.printLicense(contentOfWebpage, contentOfWebpageInArrayList);
	}
	
	
	public String readFirstPOMPageToString() throws FileNotFoundException { //The entire POM file is moved into a 
		Scanner examplePOMFile = new Scanner(pomFile);                      // String that retains the file's structure.
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
	
	
	public String webpage(String a) throws IOException{	//Opens the URL from the POM file, then reads and saves the
		URL theURL = new URL(a);                        // contents of the entire webpage.
        BufferedReader in = new BufferedReader(new InputStreamReader(theURL.openStream()));

        String inputLine; 
        String webpageWithLicense = "";
        while ((inputLine = in.readLine()) != null) {
            webpageWithLicense += inputLine;
        }
        in.close();
        
        return webpageWithLicense;
	}
	
	public ArrayList<String> putWebpageInArrayList(String a){      
        ArrayList<String> entireWebpage = new ArrayList<>();
        
        for (String segment: a.split(" ")){
            entireWebpage.add(segment);
         }

        return entireWebpage;	
	}
	
	
    public void printLicense(String a, ArrayList<String> b) throws FileNotFoundException{
    	File licenses = new File ("/Users/maramuslea/git/TestDiff2/files/approvedLicensesFullName"); //Prepares to work with 
		ArrayList<String> licensesInArray = new ArrayList<String>();                         //the Approved Licenses file.
		Scanner forLicenses = new Scanner(licenses);
		
		while(forLicenses.hasNextLine()){ //Puts the Approved Licenses file into an ArrayList, where each index is a 
			licensesInArray.add(forLicenses.nextLine());	// separate license.  
		}
		forLicenses.close();
		
		for(int i = 0; i < licensesInArray.size(); i++){ //Cross references the ArrayList with the licenses against
			if(a.contains(licensesInArray.get(i))){     //the contents of the webpage.
				System.out.println(licensesInArray.get(i));
				
				int license = b.lastIndexOf(licensesInArray.get(i).substring(0, licensesInArray.get(i).indexOf(" ")));
				
				for(int j = license; j< license + 20; j++){
					System.out.print(b.get(j));
				}
			}
		}
	}
}
