import java.io.*;
import java.net.*;
 
public class WGet { //for saving files from a given url
  public static void main(String[] args) throws Exception {
    String s;
    String urlName = "https://repo1.maven.org/maven2/cglib/cglib-nodep/2.2/cglib-nodep-2.2.pom";
    BufferedReader r = new BufferedReader(new InputStreamReader(new URL(urlName).openStream()));
    
    String fileName = urlName.substring(urlName.lastIndexOf('/') + 1); //makes name of file the final section of the url after the last "/"
    String fileLoc = "files/"; //sets location for file to be saved
    String fileDest = fileLoc + fileName; //sets full file name with destination included
    File file = new File(fileDest); // creates file
    
    //may be an issue with similar or identical urls, need to add program control for these possibilities
    //ask to double check ^^
    if (!file.exists()){ //should add else for possibility of same file--would have same end extension
    	PrintWriter writer = new PrintWriter(file);
    	while ((s = r.readLine()) != null) { //while lines are not null
    		writer.println(s); //writes lines to the file
    	}
    	writer.close(); //closes file writer
    }
    
    
  }
}
