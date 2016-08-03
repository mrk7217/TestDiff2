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
	
	
	public String getLicense(String fileName,ArrayList<String> licensesFullNames, ArrayList<String> licensesAcronyms) throws IOException{
		pomFile = new File(fileName);
		String pomContent;
		pomContent = readFirstPOMPageToString();
		
		String goodURL = findURL(pomContent);
		String contentOfWebpage = webpage(goodURL);
		String fullName = returnLicenseIfFullName(contentOfWebpage, licensesFullNames, licensesAcronyms);
		
		String acr = returnLicenseIfAcronym(contentOfWebpage, licensesAcronyms);
		return(fullName + " " + acr);
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
	
	public String webpage(String a) throws IOException{	//Opens the URL from the POM file, then reads and saves the contents of the entire web page.
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
	
    public String returnLicenseIfFullName(String a, ArrayList<String> licenseNames, ArrayList<String> licenseAcrs){
    	String license = "";
		for(int i = 0; i < licenseNames.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(licenseNames.get(i))){
				//if((i<licenseAcrs.size())){ //for getting just acronyms but must line up names and acronyms in text file
					//license += (licenseAcrs.get(i) + ", ");
					//System.out.println(licenseAcrs.get(i));}
				//else{
				license += (licenseNames.get(i) + ", ");
			}
		}		
		return license;
	}

    public String returnLicenseIfAcronym(String a, ArrayList<String> licenseNames) {
    	String license = "";
    	for(int i = 0; i < licenseNames.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(licenseNames.get(i))){
				license += (licenseNames.get(i) + ", " );
			}
		}
		return license;
    }
}
