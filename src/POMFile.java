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
		String fullName = returnLicenseIfFullName(contentOfWebpage, licensesFullNames);
		
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
	
    public String returnLicenseIfFullName(String a, ArrayList<String> b) throws FileNotFoundException{
    	String licenseName = "";
		for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(b.get(i))){     
				licenseName += (b.get(i) + ", ");
			}
		}
		if (licenseName.isEmpty())
			return licenseName;
		else
			return licenseName.substring(0, licenseName.length()-2);
	}

    public String returnLicenseIfAcronym(String a, ArrayList<String> b) {
    	String licenseAcr = "";
    	for(int i = 0; i < b.size(); i++){ //Cross references the ArrayList with the licenses against the contents of the webpage.
			if(a.contains(b.get(i))){
				licenseAcr += (b.get(i) + ", ");
				licenseAcr = licenseAcr.substring(0, licenseAcr.length()-2);
			}
		}
    	if (licenseAcr.isEmpty())
			return licenseAcr;
		else
			return licenseAcr.substring(0, licenseAcr.length()-2);
    }
}
