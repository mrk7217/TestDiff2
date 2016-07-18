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
		pom.printLicense(contentOfWebpage);
	}
	
	
	public String readFirstPOMPageToString() throws FileNotFoundException {
		Scanner examplePOMFile = new Scanner(pomFile); 
		String result = "";
		
		while(examplePOMFile.hasNextLine()){ 
			result += examplePOMFile.nextLine() + "\n";
		}
		examplePOMFile.close();
		
		return result;
	}
	
		
	public String findURL(String a){
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
	
	
	public String webpage(String a) throws IOException{	
		URL theURL = new URL(a);
        BufferedReader in = new BufferedReader(new InputStreamReader(theURL.openStream()));

        String inputLine; 
        String webpageWithLicense = "";
        while ((inputLine = in.readLine()) != null) {
            webpageWithLicense += inputLine;
        }
        in.close();

        return webpageWithLicense.toLowerCase();
	}
	
	
    public void printLicense(String a) throws FileNotFoundException{
    	File licenses = new File ("/Users/maramuslea/git/TestDiff2/files/approvedLicenses");
		ArrayList<String> licensesInArray = new ArrayList<String>();
		Scanner forLicenses = new Scanner(licenses);
		
		while(forLicenses.hasNextLine()){
			licensesInArray.add(" " + forLicenses.nextLine().toLowerCase() + " ");	
		}
		forLicenses.close();
		
		for(int i = 0; i < licensesInArray.size(); i++){
			if(a.contains(licensesInArray.get(i))){
				System.out.println(licensesInArray.get(i));
			}
		}
	}
}
