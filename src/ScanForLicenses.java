import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ScanForLicenses { //This code will take the URL of a text file and scan the file for the license specified.
	
	private final static File wApache = new File("/Users/maramuslea/Documents/wApache.txt"); // the file with Apache 2.0
	static ArrayList<String> wApacheArray = new ArrayList<String>(); // the ArrayList to insert the file into
	
	public static void main(String args[]) throws FileNotFoundException {
		Scanner wApacheScanner = new Scanner(wApache); // the Scanner to put the file into in order to change the file into an array
		
		while(wApacheScanner.hasNextLine()){ //while loop to insert Scanner into ArrayList
			String line = wApacheScanner.nextLine();
			wApacheArray.add(line);
			//System.out.println(line);
		}
		
		
		Collection<String> licenses = new ArrayList<String>(Arrays.asList("Apache License 2.0", "AFL 3.0"));
		Collection<String> file = new ArrayList<String>(wApacheArray);
		
		ArrayList<String> sourceList = new ArrayList<String>(licenses);
	    ArrayList<String> destinationList = new ArrayList<String>(file);
	    
	    file.removeAll(licenses); //fix
	    
	    System.out.print(file); //fix
	    
	    
		
		
		
	}
}
