import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ScanForLicenses { //This code will take the URL of a text file and scan the file for the license specified.
	
	private final static File wApache = new File("/Users/maramuslea/Documents/wApache.txt"); 
	private final static File wLicenses = new File("/Users/maramuslea/Documents/licensesList.txt"); 
	static ArrayList<String> wApacheArray = new ArrayList<String>(); 
	static ArrayList<String> wLicensesArray = new ArrayList<String>(); 
	private static Scanner wApacheScanner;
	private static Scanner wLicensesScanner;
	static ArrayList<String> wApacheArraySpaces = new ArrayList<String>();
	
	public static void main(String[] args) throws FileNotFoundException{
		createArray();
		createSpaces();
		compare();
	}
	
	public static void createArray() throws FileNotFoundException { // This method takes the two files 
		wApacheScanner = new Scanner(wApache);                     // and inserts them into their respective 
		wLicensesScanner = new Scanner(wLicenses);                 // ArrayLists based on where the /n are created.
		
		while(wApacheScanner.hasNextLine()){ 
			String line = wApacheScanner.nextLine();
			wApacheArray.add(line);
		}
		
		while(wLicensesScanner.hasNextLine()){
			String line = wLicensesScanner.nextLine();
			wLicensesArray.add(line);
		}
	}
	
	public static void createSpaces(){ // This method takes wApacheArray and breaks the indices by individual words.
	    String temp = ""; 
	    
	    for(int d = 0; d < wApacheArray.size(); d++){
	    	for(int e = 0; e < wApacheArray.get(d).length(); e++){
	            if(wApacheArray.get(d).charAt(e) != ' ' && e == wApacheArray.get(d).length()-1){
	            	temp += wApacheArray.get(d).charAt(e);
	            	wApacheArraySpaces.add(temp);
	            	temp = "";
	            }
	            
	            if(wApacheArray.get(d).charAt(e) != ' '){
	            	temp += wApacheArray.get(d).charAt(e);
	            }
	             			
	  			else if(wApacheArray.get(d).charAt(e) == ' '){
	  				wApacheArraySpaces.add(temp);
	            	temp = "";
	  			}
	        }
		}
	}

	public static void compare(){ // This method compares the licenseList file and the URL file.
		for(int i = 0; i< wApacheArraySpaces.size(); i++) {
			String a = wApacheArraySpaces.get(i);
	    	
	    	for(int j= 0; j< wLicensesArray.size(); j++){
	    		if (a.equals(wLicensesArray.get(j))){
	    			System.out.println(a);
	    		}
	    	}
	    }
	}
}
