import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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
		pom.printLicense(contentOfWebpage, goodURL);
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

        return webpageWithLicense;
	}
	
	
    public void printLicense(String a, String b){
		if(a.contains(" MPL ")){
        	System.out.println("MPL 2.0 or EPL 1.0");
        }
        
        if((a.contains(" Apache ") && !a.contains(" MIT ")) || b.contains("appengine") || b.contains("webtoolkit")){
        	System.out.println("Apache 2.0");
        }
        
        if(a.contains(" BSD ") && !a.contains(" Apache ")){
        	System.out.println("BSD License: unknown if BSD-2-Clause or BSD-3-Clause");
        }
        
        if(a.contains(" MIT ")){
        	System.out.println("MIT");
        }
        
        if(a.contains(" Jython ")){
        	System.out.println("Python 2.0");
        }
        
        if(a.contains("ECL-2.0")){
        	System.out.println("ECL 2.0");
        }
        
        if(b.contains("postgresql")){
        	System.out.println("PostgreSQL");
        }
        
        if(b == ""){
        	System.out.println("LGPL 3.0");
        }
        
        if(a.contains("Christian Schulte")){
        	System.out.println("BSD-2-Clause");
        }
	}
}
